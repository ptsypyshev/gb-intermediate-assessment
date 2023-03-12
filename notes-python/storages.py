import json
from datetime import datetime
from models import Note

class JSONStorage:
    """Storage implementation which uses JSON file to store data

    Attributes
    ----------
    __filepath : str
        Path to JSON file with data
    """
    
    def __init__(self, filepath: str) -> None:
        """Default constructor for Notes instance
        
        Parameters:
            __filepath: Path to JSON file with data (str)
        """
        
        self.__filepath = filepath

    def load(self) -> dict[int, Note]:
        """Load data from JSON file

        Returns:
            Dictionary with all notes from JSON file or
            if the file unavailable or corrupted it returns empty dictionary
        """
        
        try:
            with open(self.__filepath, encoding="utf-8") as f:
                notes_dict = {}
                json_obj = json.load(f)
                for key, value in json_obj.items():
                    int_key = int(key)
                    note = Note(
                        value["id"],
                        value["title"],
                        value["content"],
                        datetime.fromisoformat(value["created"]),
                        datetime.fromisoformat(value["modified"])
                    )
                    notes_dict[int_key] = note
                return notes_dict
        except (FileNotFoundError, json.decoder.JSONDecodeError, ValueError):
            return {}
        

    def save(self, notes:dict[int,Note]) -> bool:
        """Save data to JSON file
        
        Parameters:
            notes: dictionary with notes (dict[int,Note])

        Returns:
            True if all data was saved to file
        """
        
        try:
            with open(self.__filepath, "w", encoding="utf-8") as f:
                json.dump(notes, f, cls=NoteEncoder, indent=4)
            return True
        except Exception:
            return False
        

class NoteEncoder(json.JSONEncoder):
    """Encodes note objects to JSON objects"""
    
    def default(self, note:Note):
        return {
            "id": note.get_id(),
            "title": note.get_title(),
            "content": note.get_content(),
            "created": note.get_created().isoformat(),
            "modified": note.get_modified().isoformat()
        }
