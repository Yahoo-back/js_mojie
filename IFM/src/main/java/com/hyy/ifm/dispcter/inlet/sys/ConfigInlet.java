package com.hyy.ifm.dispcter.inlet.sys;

import com.hyy.ifm.config.service.ConfigDictService;
import com.hyy.ifm.config.service.ConfigSpreadService;
import com.hyy.ifm.dispcter.inlet.Inlet;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * @Author 毛椅俊
 * @Date 2018-03-26 9:40
 * @Description 配置管理
 */
@IocBean(scope="singleton", singleton=true)
public class ConfigInlet extends Inlet {

    private static final long serialVersionUID = -5977159595952060274L;

    @Inject
    private ConfigDictService configDictService;
    @Inject
    private ConfigSpreadService configSpreadService;

    @Override
    public void init() {
        /**
         * 根据data_type查询字段表
         */
        serviceTreeMap.put("qryDictByDataType", configDictService);

        serviceTreeMap.put("qryDictByShow", configDictService);

        serviceTreeMap.put("saveConfig", configDictService);


        /**
         * 查询推广配置列表
         */
        serviceTreeMap.put("qrySpreadConfigList", configSpreadService);

        /**
         * 导出
         */
        serviceTreeMap.put("exportSpreadConfigList", configSpreadService);

        /**
         * 保存
         */
        serviceTreeMap.put("saveSpreadConfig", configSpreadService);

        /**
         * 根据id查询
         */
        serviceTreeMap.put("qrySpreadConfigById", configSpreadService);

        /**
         * 更新
         */
        serviceTreeMap.put("updateSpreadConfig", configSpreadService);

        /**
         * 查询列表
         */
        serviceTreeMap.put("qryDictConfigList", configDictService);

        /**
         * 导出
         */
        serviceTreeMap.put("exportDictConfigList", configDictService);

        /**
         * 保存
         */
        serviceTreeMap.put("saveConfigDict", configDictService);

        /**
         * 根据id查询
         */
        serviceTreeMap.put("qryDictById", configDictService);

        /**
         * 更新
         */
        serviceTreeMap.put("updateConfigDict", configDictService);
    }

}
