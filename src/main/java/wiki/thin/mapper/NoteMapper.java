package wiki.thin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import wiki.thin.entity.Note;

@Mapper
public interface NoteMapper extends BaseMapper<Note> {
}