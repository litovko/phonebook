package commands;

import controllers.ApplicationContext;
import model.Book;
import model.Params;
import model.Person;
import model.Phone;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 10
 */
public class SupportedCommands extends CommandFacroryBase
{
    public SupportedCommands()
    {
        register(CommandAdd.NAME,  new CommandAddBuilder());
        register(CommandList.NAME, new CommandList());
        register(ExitCommand.NAME, new ExitCommand());
    }

    public static class CommandAdd implements Command
    {
        public static final String NAME = "add";


        public CommandAdd(String person, String phone)
        {
            this.person = person;
            this.phone = phone;
        }

        @Override
        public void execute(Book model, ApplicationContext ap)
        {
            Person person = new Person(this.person);

            person.getPhones().add(new Phone(person, this.phone));
            model.getPersons().add(person);

            System.out.println(getName() + ": person " + this.person + " was added to the book, phone is: " + this.phone);
        }

        @Override
        public String getName() {
            return NAME;
        }

        private String person;
        private String phone;
    }


    public static class CommandAddBuilder implements CommandBuilder
    {
        @Override
        public Command createCommand(Params params)
        {
            String[] args = null;

            if (StringUtils.isNotEmpty(params.getCommandArgs()))
                args = StringUtils.split(params.getCommandArgs());

            if (args == null || args.length != 2)
                return UnknownCommand.getInstance();

            return new CommandAdd(args[0], args[1]);
        }
    }


    public static class CommandList implements Command, CommandBuilder
    {
        public static final String NAME = "list";

        @Override
        public void execute(Book model, ApplicationContext ap)
        {
            List<Person> copy = new ArrayList<>(model.getPersons());
            Collections.sort(copy, new Comparator<Person>()
            {
                @Override
                public int compare(Person o1, Person o2)
                {
                    return o1.getName().compareToIgnoreCase(o2.getName());
                }
            });

            for (Person p : copy)
                printPerson(p);

        }

        private void printPerson(Person person)
        {
            StringBuilder sb = new StringBuilder();
            sb.append("Person: ").append(person.getName()).append("\n").append("phones: ");
            for (Phone phone : person.getPhones())
                sb.append(phone.getPhone()).append("\n");

            System.out.println(sb.toString());
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        public Command createCommand(Params params) {
            return new CommandList();
        }
    }
}
