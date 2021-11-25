package wiki.thin.web.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import wiki.thin.entity.Note;
import wiki.thin.mapper.NoteMapper;
import wiki.thin.web.vo.NoteModifyVO;
import wiki.thin.web.vo.ResponseVO;

@RestController
@RequestMapping("/api/note")
@RequiredArgsConstructor
public class NoteApiController {
    private final NoteMapper noteMapper;

    @PostMapping
    public ResponseVO save(@RequestBody NoteModifyVO noteModifyVO) {
        Note note = new Note();
        note.setContent(noteModifyVO.getContent());
        noteMapper.insert(note);
        return ResponseVO.successWithData(note.getId());
    }

    @PutMapping("/{postId}")
    public ResponseVO update(@PathVariable Long postId, @RequestBody NoteModifyVO noteModifyVO) {

        final Note note = noteMapper.selectById(postId);
        if (note == null) {
            return ResponseVO.error("找不到指定记录");
        }

        note.setContent(noteModifyVO.getContent());
        noteMapper.updateById(note);
        return ResponseVO.successWithData(note.getId());
    }

    @DeleteMapping("/{postId}")
    public ResponseVO delete(@PathVariable Long postId) {
        noteMapper.deleteById(postId);
        return ResponseVO.success();
    }

    @GetMapping
    public ResponseVO<Page<Note>> page(@RequestParam("page") int currentPage, @RequestParam("pageSize") int pageSize) {
        Page<Note> page = new Page<>(currentPage, pageSize);
        QueryWrapper<Note> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("created_date");
        Page<Note> notePage = noteMapper.selectPage(page, wrapper);

        return ResponseVO.successWithData(notePage);
    }
}
