package per.zpp.mapper;

import per.zpp.domain.Floor;
import per.zpp.vo.FloorVo;

import java.util.List;

/**
 * @Author 想去外太空的
 * @Date 2023/6/6 17:07
 * @Version 1.0 （版本号）
 */
public interface FloorMapper {

    List<Floor> findFloorList(FloorVo floorVo);


        int addFloor(Floor floor);

        int updateFloor(Floor floor);

}

