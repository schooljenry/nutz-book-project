package net.wendal.nutzbook.module;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import net.wendal.nutzbook.service.sysinfo.SysInfoService;
import net.wendal.nutzbook.util.Toolkit;

@IocBean
@At("/sysinfo")
public class SysInfoModule extends BaseModule {
    
    @Inject
    protected SysInfoService sysInfoService;

    @At("/")
    @Ok("beetl:yvr/sysinfo/index.btl")
    @Fail("http:500")
    public NutMap index(){
        NutMap re = new NutMap();
        re.put("current_user", fetch_userprofile(Toolkit.uid()));
        return re;
    }
    
    @At
    @Ok("json:full")
    public Object query(@Param("match")String match) {
        return sysInfoService.fetch(match);
    }
}
