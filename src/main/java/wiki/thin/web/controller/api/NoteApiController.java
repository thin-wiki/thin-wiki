package wiki.thin.web.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import wiki.thin.entity.Note;
import wiki.thin.repo.NoteAutoRepo;
import wiki.thin.web.vo.NoteModifyVO;
import wiki.thin.web.vo.ResponseVO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/note")
@AllArgsConstructor
public class NoteApiController {
    private final NoteAutoRepo noteAutoRepo;

    @PostMapping
    public ResponseVO save(@RequestBody NoteModifyVO noteModifyVO) {
        Note note = new Note();
        note.setContent(noteModifyVO.getContent());
        noteAutoRepo.save(note);
        return ResponseVO.successWithData(note.getId());
    }

    @PutMapping("/{postId}")
    public ResponseVO update(@PathVariable Long postId, @RequestBody NoteModifyVO noteModifyVO) {

        final Optional<Note> noteOptional = noteAutoRepo.findById(postId);
        if (noteOptional.isEmpty()) {
            return ResponseVO.error("找不到指定记录");
        }

        Note note = noteOptional.get();
        note.setContent(noteModifyVO.getContent());
        noteAutoRepo.save(note);
        return ResponseVO.successWithData(note.getId());
    }

    @DeleteMapping("/{postId}")
    public ResponseVO delete(@PathVariable Long postId) {
        noteAutoRepo.deleteById(postId);
        return ResponseVO.success();
    }

    @GetMapping
    public ResponseVO<Page<Note>> page(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        final Sort sort = Sort.by(Sort.Order.desc("createdDate"));
        final PageRequest pageRequest = PageRequest.of(page, pageSize, sort);

        final Page<Note> pageAll = noteAutoRepo.findAll(pageRequest);

        return ResponseVO.successWithData(pageAll);
    }
}
