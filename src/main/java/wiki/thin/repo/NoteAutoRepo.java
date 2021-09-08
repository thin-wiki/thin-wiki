package wiki.thin.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import wiki.thin.entity.Note;

public interface NoteAutoRepo extends JpaRepository<Note, Long> {
}
