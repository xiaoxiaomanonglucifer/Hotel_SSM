package per.zpp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.zpp.domain.Floor;
import per.zpp.mapper.FloorMapper;
import per.zpp.service.FloorService;
import per.zpp.vo.FloorVo;

import java.util.List;

/**
 * @Author 想去外太空的
 * @Date 2023/6/6 17:07
 * @Version 1.0 （版本号）
 */
@Service
@Transactional
public class FloorServiceImpl implements FloorService {

    @Autowired
    private FloorMapper floorMapper;
    @Override
    public List<Floor> findFloorList(FloorVo floorVo) {
        return floorMapper.findFloorList(floorVo);
    }

    @Override
    public int addFloor(Floor floor) {
        return floorMapper.addFloor(floor);
    }

    @Override
    public int updateFloor(Floor floor) {
        return floorMapper.updateFloor(floor);
    }
}
