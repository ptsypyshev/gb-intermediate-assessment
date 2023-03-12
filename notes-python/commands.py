import msg
from models import Notes, Note
from view import View
from datetime import datetime

class Command:
    """Base Command Class"""
    def __init__(self, notes:Notes, view:View) -> None:
        """Default constructor for Command instances

        Parameters:
            notes: notes instance (Notes)
            view: view instance (View)
        """
        self._notes = notes
        self._view = view
    
    def execute(self) -> None:
        """Execute method for command"""
        raise NotImplementedError()

    def __str__(self) -> str:
        """String representation for command"""
        raise NotImplementedError()


class Invoker:
    """Class used to invoke commands"""
    def __init__(self) -> None:
        """Default constructor for Invoker instances"""
        self.__commands = {}
    
    def add_command(self, cmd_id:int, cmd:Command) -> None:
        """Add command to invoker

        Parameters:
            cmd_id: command identifier (int)
            cmd: command instance (Command)
        """
        self.__commands[cmd_id] = cmd
    
    def get_command(self, cmd_id:int) -> Command:
        """Get command from invoker by its ID

        Parameters:
            cmd_id: command identifier (int)
        
        Returns:
            Command instance
        """
        return self.__commands.get(cmd_id)
    
    def get_commands(self) -> dict[int, Command]:
        """Get all commands from invoker
        
        Returns:
            dict with all Command instances
        """
        return self.__commands


class Exit(Command):
    """Exit Command"""
    def execute(self) -> None:
        pass

    def __str__(self) -> str:
        return "Exit"


class AddNote(Command):
    """Add Note Command"""
    def __init__(self, notes: Notes, view: View) -> None:
        super().__init__(notes, view)
    
    def execute(self) -> None:
        note_id = self._notes.get_next_note_id()
        note_title = input(msg.INPUT_NOTE_TITLE_MSG)
        note_content = input(msg.INPUT_NOTE_CONTENT_MSG)
        if self._notes.create(Note(note_id, note_title, note_content)):
            self._view.print_msg(msg.NOTE_ADDED_MSG + "\n")

    def __str__(self) -> str:
        return "Add note"

class ReadNote(Command):
    """Read Note Command"""
    def __init__(self, notes: Notes, view: View) -> None:
        super().__init__(notes, view)
    
    def execute(self) -> None:
        note_id = self._view.get_int(msg.INPUT_NOTE_ID_MSG)
        note = self._notes.read(note_id)
        if note:
            self._view.print_msg(f"Title: {note.get_title()}\nContent: {note.get_content()}\n" + 
            f"Created: {note.get_created()}\nModified: {note.get_modified()}\n")
        else:
            self._view.print_msg(msg.INVALID_NOTE_ID_MSG + "\n")

    def __str__(self) -> str:
        return "Read note"


class ReadNotes(Command):
    """Read Notes Command"""
    def __init__(self, notes: Notes, view: View) -> None:
        super().__init__(notes, view)
    
    def execute(self) -> None:
        for note in self._notes:
            self._view.print_msg(f"Note #{note.get_id()} - {note.get_title()}")
        self._view.print_msg("")

    def __str__(self) -> str:
        return "Read notes"


class EditNote(Command):
    """Edit Note Command"""
    def __init__(self, notes: Notes, view: View) -> None:
        super().__init__(notes, view)
    
    def execute(self) -> None:
        note_id = self._view.get_int(msg.INPUT_NOTE_ID_MSG)
        orig_note = self._notes.read(note_id)
        if orig_note:
            note_title = input(msg.INPUT_NOTE_TITLE_MSG)
            note_content = input(msg.INPUT_NOTE_CONTENT_MSG)
            if self._notes.update(Note(note_id, note_title, note_content)):
                self._view.print_msg(msg.NOTE_EDITED_MSG + "\n")
        else:
            self._view.print_msg(msg.INVALID_NOTE_ID_MSG + "\n")

    def __str__(self) -> str:
        return "Edit note"
    

class RemoveNote(Command):
    """Remove Note Command"""
    def __init__(self, notes: Notes, view: View) -> None:
        super().__init__(notes, view)
    
    def execute(self) -> None:
        note_id = self._view.get_int(msg.INPUT_NOTE_ID_MSG)
        if self._notes.delete(note_id):
            self._view.print_msg(msg.NOTE_REMOVED_MSG + "\n")
        else:
            self._view.print_msg(msg.INVALID_NOTE_ID_MSG + "\n")

    def __str__(self) -> str:
        return "Remove note"


class SearchNote(Command):
    """Search Note by contains substring Command"""
    def __init__(self, notes: Notes, view: View) -> None:
        super().__init__(notes, view)
    
    def execute(self) -> None:
        flag_find = False
        search_str = input(msg.INPUT_SEARCH_STR_MSG).lower()
        for note in self._notes:
            if note.get_title().lower().find(search_str) != -1 or note.get_content().lower().find(search_str) != -1:
                if not flag_find:
                    self._view.print_msg(msg.NOTES_FOUND_MSG)
                self._view.print_msg(f"Note #{note.get_id()} - {note.get_title()}")
                flag_find = True
        if not flag_find:
            self._view.print_msg(msg.NOTES_NOT_FOUND_MSG + "\n")   
        else:
            self._view.print_msg("")     

    def __str__(self) -> str:
        return "Search note (find word or phrase)"


class SearchNoteByDate(Command):
    """Search Note by date Command"""
    def __init__(self, notes: Notes, view: View) -> None:
        super().__init__(notes, view)
    
    def execute(self) -> None:
        flag_find = False
        start_date_str = input(msg.INPUT_SEARCH_START_DATE_STR_MSG)
        end_date_str = input(msg.INPUT_SEARCH_END_DATE_STR_MSG)
        try:
            start_date = datetime.strptime(start_date_str, "%Y-%m-%d")
            end_date = datetime.strptime(end_date_str, "%Y-%m-%d")
        except ValueError:
            self._view.print_msg(msg.INVALID_DATE_STR_MSG + "\n")
            return        

        for note in self._notes:
            if start_date < note.get_modified() < end_date:
                if not flag_find:
                    self._view.print_msg(msg.NOTES_FOUND_MSG)
                self._view.print_msg(f"Note #{note.get_id()} - {note.get_title()}")
                flag_find = True
        if not flag_find:
            self._view.print_msg(msg.NOTES_NOT_FOUND_MSG + "\n")   
        else:
            self._view.print_msg("")     

    def __str__(self) -> str:
        return "Search note by date"
