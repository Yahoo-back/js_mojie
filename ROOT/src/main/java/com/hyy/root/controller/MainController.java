package com.hyy.root.controller;

import com.alibaba.fastjson.JSONObject;
import com.hyy.root.pojo.Logins;
import com.hyy.root.pojo.Logins.Login;
import com.hyy.root.util.Constant;
import com.hyy.root.util.IPUtils;
import com.hyy.root.util.ToolUtils;
import com.hyy.root.util.sharekey.SysShareKey;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import com.hyy.root.pojo.FileEntity;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 毛椅俊
 *
 */
@IocBean
@Ok("json")
@Fail("http:500")
@Filters(@By(type=CheckSession.class, args={SysShareKey.LoginSession, SysShareKey.LoginUrl}))
public class MainController extends BaseController {

	@At("/main/api")
	@Ok("json")
	@Fail("jsp:main.index")
	public void api(HttpServletRequest req, HttpSession session, HttpServletResponse resp)
			throws Exception {
		String url2=req.getScheme()+"://"+ req.getServerName();//+request.getRequestURI();
		// TODO 简单的session 验证
		String page = ToolUtils.nvl(req.getParameter("page"));
		String pageSize = ToolUtils.nvl(req.getParameter("rows"));
		String cmd = ToolUtils.nvl(req.getParameter("cmd"));
		String rows = URLDecoder.decode(ToolUtils.nvl(req.getParameter("cnds")), "UTF-8");
		Map<String, Object> map = new HashMap<String, Object>();
		Logins.Login logins = (Login) session.getAttribute(SysShareKey.LoginSession);
		if(null == logins && !"login".equals(cmd)){
			makeJSONObject(resp, "{'resultNode':'session失效，请重新登录！'}");
			throw new Exception("session失效");
		}
		/*if(ToolUtils.nvl(logins.getAccountType()).equals("")){
			makeJSONObject(resp, "{'resultNode':'账户异常请联系管理员！'}");
			throw new Exception("账户异常请联系管理员！");
		}*/
		map.put("cmd", cmd);
		map.put("pageNum", "".equals(page) ? "0" : page);
		map.put("userCode", null== logins ? "" : logins.getUserId());
		map.put("token", null== logins ? "" : logins.getToken());
		map.put("role_filter", null== logins ? "" : logins.getRole_filter());
		map.put("pageSize", "".equals(ToolUtils.nvl(pageSize)) ? "10" : pageSize);
		map.put("accountType", null== logins ? "" : logins.getAccountType());
		map.put("address", IPUtils.getIpAddr(req));
		map.put("rows", "".equals(rows) ? "{}" : rows);
		// 如果是生成环境做域名的yanzheng
		/*if("pro".equals(Constant.SYS_ENV)){
			boolean ispass = false ;
			if("0".equals(logins.getAccountType()) && url2.equals("http://www.xiaoyuehui-core.com")){
				ispass = true ;
			}else if("1".equals(logins.getAccountType()) && url2.equals("http://www.xiaoyuehui-me.com")){
				ispass = true ;
			}
			if(!ispass){
				makeJSONObject(resp, "{'resultNode':'登录域名不正确，请联系渠道取得正确的访问域名！！'}");
				throw new Exception("登录域名不正确，请联系渠道取得正确的访问域名！！");
			}
		}*/
		try {
			String res = super.apiDispcter.doProcess(map);
			makeJSONObject(resp, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@At("/upload")
	public void upload(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		PrintWriter out = resp.getWriter();
		Map<String, String> map = new HashMap<String, String>();
		List<FileEntity> fes = new ArrayList<FileEntity>();
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory() ;
			String realPath = Constant.UPLOAD_ADDR;
			String zjuri = "/fileUpload/upload/" + new SimpleDateFormat("yyyyMMdd").format(new Date())+"/";
			File nfile = new File(realPath+zjuri);
			if (!nfile.isDirectory()) {
				nfile.mkdirs();
			}
			factory.setSizeThreshold( 8192 ) ;
			factory.setRepository( nfile ) ;
			ServletFileUpload upload = new ServletFileUpload( factory ) ;

			@SuppressWarnings("unchecked")
			List<FileItem> items = upload.parseRequest(req);
			Iterator<FileItem> itr = items.iterator();
			while (itr.hasNext()) {// 依次处理每个 form field
				FileItem fitem = (FileItem) itr.next();
				if(!fitem.isFormField()){ /* 判断是否为表单控件（非File控件），如果不是表单控件，则上传此文件 */
					String filename = fitem.getName();
					long size = fitem.getSize();
					// 对于未上传文件的情况则跳过
					if (filename == null || filename.equals("") || size == 0) {
						continue;
					}

					// 为获取文件的扩展名
					int dot = filename.lastIndexOf(".");
					// 扩展名有带前面的.号
					String extname = filename.substring(dot);

					// 为避免文件覆盖，使用当前时间产生新文件名
					String newname = String.valueOf((new Date()).getTime());

					String newUrl = realPath + zjuri + newname + extname;
					File destFile = new File(newUrl);
					inputstreamtofile(fitem.getInputStream(),destFile);
					FileEntity fe = new FileEntity();
					fe.setFile_uri(zjuri + newname + extname);
					fe.setId(String.valueOf((new Date()).getTime()));
					fe.setOldName(filename);
					fes.add(fe);
				}
			}
			map.put("fes", Json.toJson(fes));
			map.put("resultNode", "success");
			out.write(gson.toJson(map));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultNode", "上传失败");
			out.write(gson.toJson(map));
		} finally {
			if (null != out) {
				out.close();
			}
		}
	}

	@At("/editorUpload")
	public JSONObject save(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		StringBuilder rs = new StringBuilder();

		try {
			DiskFileItemFactory factory = new DiskFileItemFactory() ;
			String realPath = Constant.UPLOAD_ADDR;
			String zjuri = "/fileUpload/upload/" + new SimpleDateFormat("yyyyMMdd").format(new Date())+"/";
			File nfile = new File(realPath+zjuri);
			if (!nfile.isDirectory()) {
				nfile.mkdirs();
			}
			factory.setSizeThreshold( 8192 ) ;
			factory.setRepository( nfile ) ;
			ServletFileUpload upload = new ServletFileUpload( factory ) ;

			List<FileItem> items = upload.parseRequest(req);
			Iterator<FileItem> itr = items.iterator();
			List<String> imgUrl = new ArrayList<String>();
			while (itr.hasNext()) {// 依次处理每个 form field
				FileItem fitem = (FileItem) itr.next();
				if(!fitem.isFormField()){ /* 判断是否为表单控件（非File控件），如果不是表单控件，则上传此文件 */
					String filename = fitem.getName();
					long size = fitem.getSize();
					// 对于未上传文件的情况则跳过
					if (filename == null || filename.equals("") || size == 0) {
						continue;
					}

					// 为获取文件的扩展名
					int dot = filename.lastIndexOf(".");
					// 扩展名有带前面的.号
					String extname = filename.substring(dot);

					// 为避免文件覆盖，使用当前时间产生新文件名
					String newname = String.valueOf((new Date()).getTime());

					String newUrl = realPath + zjuri + newname + extname;
					File destFile = new File(newUrl);
					inputstreamtofile(fitem.getInputStream(),destFile);
					imgUrl.add(zjuri + newname + extname);
				}
			}

			rs.append("{errno:0, data:[");
			for(String img : imgUrl) {
				//todo  上线后修改
				rs.append("'" + img + "'");

			}
			rs.append("]}");

			return JSONObject.parseObject(rs.toString());
		} catch (Exception e) {
			e.printStackTrace();
			rs.append("{errno:1, data:[]");
			return JSONObject.parseObject(rs.toString());
		}
	}

	public void inputstreamtofile(InputStream ins, File file) {
		OutputStream os = null;
		try {
			os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(null != os){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				ins.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@At("/fileUpload/upload/?/?")
	public void getImage(String uploadId1, String uploadId2, HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, IOException {
		resp.setContentType("image/jpeg");

		String filePath = "D:/fileUpload/webapps/fileUpload/upload/"+uploadId1 + "/" + uploadId2 + ".jpg";
		File file = new File(filePath);
		if(file.exists()) {
			InputStream is = null;
			OutputStream os = resp.getOutputStream();
			try {
				is = new FileInputStream(file);
				resp.setHeader("Access-Control-Allow-Origin", "*");//设置该图片允许跨域访问
				IOUtils.copy(is, os);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if(is!=null) {
						is.close();
						is = null;
					}
					if(os!=null) {
						os.close();
						os = null;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
