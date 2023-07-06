package per.zpp.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.zpp.domain.Floor;
import per.zpp.service.FloorService;
import per.zpp.utils.DataGridViewResult;
import per.zpp.utils.SystemConstant;
import per.zpp.vo.FloorVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 想去外太空的
 * @Date 2023/6/6 17:06
 * @Version 1.0 （版本号）
 */
@RestController
@RequestMapping("/admin/floor")
public class FloorController {

    @Autowired
    private FloorService floorService;

    /**
     * 查询楼层列表
     * @param floorVo
     * @return
     */
    @RequestMapping("/list")
    public DataGridViewResult list(FloorVo floorVo){
//设置分页信息
        PageHelper.startPage(floorVo.getPage(),floorVo.getLimit());
        //调用查询楼层列表的方法
        List<Floor> floorList=floorService.findFloorList(floorVo);
        //创建分页对象
        PageInfo<Floor> pageInfo=new PageInfo<Floor>(floorList);

        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

    @RequestMapping("/addFloor")
    public String addFloor(Floor floor){
        Map<String, Object> map = new HashMap<>();
        if(floorService.addFloor(floor)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"添加成功");
        }else{
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"添加失败");
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping("/updateFloor")
    public String updateFloor(Floor floor){
        Map<String, Object> map = new HashMap<>();
        if(floorService.updateFloor(floor)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"修改成功");
        }else{
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"修改失败");
        }
        return JSON.toJSONString(map);
    }
}
