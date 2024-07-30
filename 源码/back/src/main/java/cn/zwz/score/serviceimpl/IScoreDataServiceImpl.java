package cn.zwz.score.serviceimpl;

import cn.zwz.score.mapper.ScoreDataMapper;
import cn.zwz.score.entity.ScoreData;
import cn.zwz.score.service.IScoreDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional
public class IScoreDataServiceImpl extends ServiceImpl<ScoreDataMapper, ScoreData> implements IScoreDataService {

    @Autowired
    private ScoreDataMapper scoreDataMapper;
}