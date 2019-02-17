package todoapp.web.todos;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import todoapp.core.todos.application.TodoEditor;
import todoapp.core.todos.application.TodoFinder;
import todoapp.core.todos.domain.Todo;

@RestController
@RolesAllowed("ROLE_USER")
public class TodoManageRestController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private TodoFinder todoFinder;
	private TodoEditor todoEditor;
	
	public TodoManageRestController(TodoFinder todoFinder, TodoEditor todoEditor) {
		this.todoFinder = todoFinder;
		this.todoEditor = todoEditor;
	}
	
	// @RequestMapping(value = "/api/todos", method = RequestMethod.GET)
	@GetMapping("/api/todos")
	public List<Todo> todos() {
		return todoFinder.getAll();
	}
	
	@PostMapping("/api/todos")
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid TodoEditorCommand command) {
		log.info("request command: {}", command);
		todoEditor.create(command.getTitle());
	}
	
	@PutMapping("/api/todos/{id}")
	public void update(@PathVariable Long id, @RequestBody @Valid TodoEditorCommand command) {
		log.info("request id: {}, command: {}", id, command);		
		todoEditor.update(id, command.getTitle(), command.isCompleted());
	}
	
	@DeleteMapping("/api/todos/{id}")
	public void delete(@PathVariable Long id) {
		log.info("request id: {}", id);
		todoEditor.delete(id);		
	}
	
	
	public static class TodoEditorCommand {
		
		@Size(min = 4, max = 140)
		private String title;
		
		private boolean completed;
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public boolean isCompleted() {
			return completed;
		}
		public void setCompleted(boolean completed) {
			this.completed = completed;
		}
		
		@Override
		public String toString() {
			return "TodoEditorCommand [title=" + title + ", completed=" + completed + "]";
		}
		
	}
	
}
