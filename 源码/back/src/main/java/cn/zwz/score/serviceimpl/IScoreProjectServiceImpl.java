package cn.zwz.score.serviceimpl;

import cn.zwz.score.mapper.ScoreProjectMapper;
import cn.zwz.score.entity.ScoreProject;
import cn.zwz.score.service.IScoreProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 评分项目 服务层接口实现
 * @author 郑为中
 */
@Slf4j
@Service
@Transactional
public class IScoreProjectServiceImpl extends ServiceImpl<ScoreProjectMapper, ScoreProject> implements IScoreProjectService {

    @Autowired
    private ScoreProjectMapper scoreProjectMapper;
}