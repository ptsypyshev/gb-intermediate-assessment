from datetime import datetime

class Note:
    """Class for Note instances

    Attributes
    ----------
    __id : int
        ID of the Note
    __title : str
        Note's title
    __content : str
        Note's content
    __created : datetime
        Datetime when the Note was created
    __modified : datetime
        Datetime when the Note was modified (last time)
    """
    
    def __set_note(self, id: int, title: str, content: str) -> None:
        """Set note fields

        Parameters:
            id: note identifier (int)
            title: note title (str)
            content: note content (str)
        """
        self.__id = id
        self.__title = title
        self.__content = content
    
    def __init__(self, id: int, title: str, content: str, created:datetime=None, modified:datetime=None) -> None:
        """Full constructor for Note instance

        Parameters:
            id: note identifier (int)
            title: note title (str)
            content: note content (str)
        """

        self.__set_note(id, title, content)
        self.__created = created if created else datetime.now()
        self.__modified = modified if modified else self.__created

    def modify(self, id: int, title: str, content: str) -> None:
        """Modifies the note

        Parameters:
            id: note identifier (int)
            title: note title (str)
            content: note content (str)
        """
        self.__set_note(id, title, content)
        self.__modified = datetime.now()

    def get_id(self) -> int:
        """Return Note ID"""
        return self.__id
    
    def get_title(self) -> str:
        """Return Note Title"""
        return self.__title
    
    def get_content(self) -> str:
        """Return Note Content"""
        return self.__content
    
    def get_created(self) -> datetime:
        """Return Note Created DateTime"""
        return self.__created

    def get_modified(self) -> datetime:
        """Return Note Modified DateTime"""
        return self.__modified
    
    def __str__(self) -> str:
        """Return String representation of Note"""
        return f"Note #{self.get_id()}\nTitle: {self.get_title()}\nContent: {self.get_content()}"

    def __repr__(self) -> str:
        """Return String representation of Note"""
        return f"Note #{self.get_id()}\nTitle: {self.get_title()}\nContent: {self.get_content()}\nCreated: {self.get_created()}\nModified: {self.get_modified()}"


class Notes:    
    """Class for Notes instances
    It can manipulate notes

    Attributes
    ----------
    __storage : interface
        Storage for notes (should implement load() and save() methods)
    __elements : dict[int, Note]
        Note's dictionary
    __next_note_id : int
        Next note ID (increments with every new note in notes)
    """

    def __init__(self, storage:object) -> None:
        """Default constructor for Notes instance
        
        Parameters:
            storage: object which can store notes (interface)
        """

        self.__storage = storage
        self.__elements = storage.load()
        self.__next_note_id = int(max(self.__elements.keys())) + 1 if self.__elements else 0
    
    def __iter__(self):
        """Iterator for Notes instance"""
        return iter(self.__elements.values())

    def get_next_note_id(self) -> int:
        """Return next note ID (incremental)"""
        return self.__next_note_id

    def create(self, note:Note) -> bool:
        """Creates new Note in Notes storage
        
        Parameters:
            note : Note instance (Note)
        
        Returns:
            True if Note was added to storage
        """
        
        self.__elements[note.get_id()] = note
        self.__next_note_id += 1
        return self.__storage.save(self.__elements)
    
    def read(self, note_id:int) -> Note:
        """Read a Note from storage by its ID
        
        Parameters:
            note_id : Note identifier (int)
        
        Returns:
            Note instance if ID is valid or None value
        """
        
        return self.__elements.get(note_id)

    def read_all(self) -> dict[int, Note]:
        """Read all notes from storage

        Returns:
            dict with all notes
        """
        return self.__elements
    
    def update(self, note:Note) -> bool:
        """Update a Note at storage by its ID
        
        Parameters:
            note_id : Note identifier (int)
        
        Returns:
            True if Note was updated at storage
        """
        note_id = note.get_id()
        orig_note = self.read(note_id)
        if orig_note:
            self.read(note_id).modify(note_id, note.get_title(), note.get_content())
            return self.__storage.save(self.__elements)
        return False
    
    def delete(self, note_id:int) -> bool:
        """Delete a Note from storage by its ID
        
        Parameters:
            note_id : Note identifier (int)
        
        Returns:
            True if Note was deleted from storage
        """
        
        orig_note = self.read(note_id)
        if orig_note:
            del self.__elements[note_id]
            return self.__storage.save(self.__elements)
        return None
