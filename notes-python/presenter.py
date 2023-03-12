import msg
from view import View
from commands import Invoker

DEFAULT_CMD_NUMBER = -1
EXIT_CMD_NUMBER = 0

class Presenter:
    """Class for Presenter instances

    Attributes
    ----------
    __view : View
        View instance to interact with user
    __invoker : Invoker
        Invoker instance to execute commands
    """
    
    def __init__(self, view:View, invoker:Invoker) -> None:
        """Default constructor for Notes instance
        
        Parameters:
            view: View instance to interact with user (View)
            invoker: Invoker instance to execute commands (Invoker)
        """
        
        self.__view = view
        self.__invoker = invoker

    def run(self) -> None:
        """Run application using Presenter instance"""
        
        self.__view.print_msg(msg.WELCOME_MSG)

        cmd_number = DEFAULT_CMD_NUMBER
        while cmd_number != EXIT_CMD_NUMBER:
            self.main_menu()
            cmd_number = self.__view.get_int(msg.GET_COMMAND_MSG)
            cmd = self.__invoker.get_command(cmd_number)
            if cmd is not None:
                cmd.execute()

        self.__view.print_msg(msg.BYE_MSG)
    
    def main_menu(self) -> None:
        """Show Main menu"""

        self.__view.print_msg(msg.COMMANDS_MENU_MSG)
        commands = self.__invoker.get_commands()
        for key, value in commands.items():
            self.__view.print_msg(f"{key} - {value}")
