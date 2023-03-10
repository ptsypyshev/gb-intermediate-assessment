import msg as m

class View:
    """Class View used to interaction with user"""

    def print_msg(self, msg:str) -> None:
        """Prints message to console
        
        Parameters:
            msg: message to print out (str)
        """
        
        print(msg)
    
    def get_int(self, msg:str) -> int:
        """Get integer value from user input and validates it
        
        Parameters:
            msg: message prompt for user (str)

        Returns:
            Integer value that get from user input
        """
        
        while True:
            try:
                return int(input(msg))
            except ValueError:
                print(m.INVALID_INPUT_MSG)