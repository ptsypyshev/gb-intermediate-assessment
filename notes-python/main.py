import os
from models import Notes
from storages import JSONStorage
from presenter import Presenter
from view import View
import commands

if __name__ == "__main__":
    # Init components    
    storage = JSONStorage(os.path.join(os.getcwd(), "notes.json"))
    notes = Notes(storage)
    view = View()
    invoker = commands.Invoker()
    
    # Add commands to invoker
    invoker.add_command(1, commands.AddNote(notes, view))
    invoker.add_command(2, commands.ReadNote(notes, view))
    invoker.add_command(3, commands.ReadNotes(notes, view))
    invoker.add_command(4, commands.EditNote(notes, view))
    invoker.add_command(5, commands.RemoveNote(notes, view))
    invoker.add_command(6, commands.SearchNote(notes, view))
    invoker.add_command(7, commands.SearchNoteByDate(notes, view))
    invoker.add_command(0, commands.Exit(notes, view))

    # Init and run Presenter
    presenter = Presenter(view, invoker)
    presenter.run()
