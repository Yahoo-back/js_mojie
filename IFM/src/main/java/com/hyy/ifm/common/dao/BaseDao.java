package com.hyy.ifm.common.dao;

import com.hyy.ifm.util.StringUtil;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.loader.annotation.Inject;

import java.util.List;

public class BaseDao {
	@Inject("dao")
	protected Dao dao;

	/**
	 * 自定义sql查询返回一个record的集合
	 * 
	 * @param sql
	 * @return
	 */
	public List<Record> queryforlist(Sql sql) {
		sql.setCallback(Sqls.callback.records());
		dao.execute(sql);
		return sql.getList(Record.class);
	}

	/**
	 * 自定义sql查询返回一个record
	 * 
	 * @param sql
	 * @return
	 */
	public Record queryforEntity(Sql sql) {
		List<Record> rs = queryforlist(sql);
		Record re = null == rs || rs.size() == 0 ? new Record() : rs.get(0);
		return re;
	}

	/**
	 * 查询记录的个数
	 * 
	 * @param sql
	 * @return
	 */
	public int queryforCount(Sql sql) {
		Record re = queryforEntity(sql);
		return StringUtil.parseInt(re.getString("count"));
	}

	public static int getPageNum(String page) {
		int pagenum = StringUtil.parseInt(page);
		return (pagenum - 1) * 10;
	}

	/**
	 * 自定义SQL分页查询返回的对象是Record
	 * 
	 * @param sqls
	 *            自定义的SQL
	 * @param page
	 *            当前页面
	 * @param size
	 *            每页显示多少条
	 * @return
	 */
	public QueryResult findPaginationBySqls(Sql sql, int page, int size) {
		// 封装QueryResult对象
		Long counts = Daos.queryCount(dao, sql.toString());
		// 定义分页
		Pager pager = dao.createPager(page, size);
		// 回调查询
		sql.setCallback(Sqls.callback.records());
		sql.setEntity(dao.getEntity(Record.class));
		sql.setPager(pager);
		dao.execute(sql);
		List<Record> list = sql.getList(Record.class);
		pager.setRecordCount(counts.intValue());
		return new QueryResult(list, pager);
	}
}
