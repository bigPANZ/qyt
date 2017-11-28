package com.panz.service.rxpic;

import com.panz.dao.mapper.SPicMapper;
import com.panz.entity.SPic;
import com.panz.entity.SPicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RxpicServiceImpl  implements RxpicService{
    @Autowired
    private SPicMapper sPicMapper;
    @Override
    public List<SPic> getRxPic(Integer Id) {
            SPicExample example = new SPicExample();
            SPicExample.Criteria criteria = example.createCriteria();
            if (!"".equals(Id == null ? "" : Id)) {
                   criteria.andIdEqualTo(Id);
            }
            return sPicMapper.selectByExample(example);
    }

    @Override
    public int addRxPic(SPic sPic) {
        return  sPicMapper.insertSelective(sPic);
    }
}
