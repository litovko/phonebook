package commands;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import controllers.ApplicationContext;
import model.Book;
import model.Params;
import model.Person;
import model.Phone;
import org.apache.commons.lang3.StringUtils;
import services.DBModule;
import services.StorageService;

import java.util.*;

/**
 * (c) Roman Gordeev
 * <p/>
 * 2014 июн 10
 */
public class SupportedCommands extends CommandFacroryBase
{
    public SupportedCommands()
    {
//<<<<<<< HEAD
      //  register(CommandAdd.NAME,  new CommandAddBuilder());
        //register(CommandList.NAME, new CommandList());
        //register(CommandExit.NAME, new CommandExit());
        register(CommandDelete.NAME, new CommandDeleteBuilder());
        register(CommandUpdate.NAME, new CommandUpdateBuilder());
//=======
        Injector injector = Guice.createInjector(new DBModule());


        register(CommandAdd.NAME,  new CommandAddBuilder(injector));
        register(CommandList.NAME, new CommandListBuilder(injector));
        register(ExitCommand.NAME, new ExitCommand());
//>>>>>>> f79e7c237e7f2e631ae8a0c3398198cb21471bb1
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

            storage.add(this.person, this.phone, model);

            System.out.println(getName() + ": person " + this.person + " was added to the book, phone is: " + this.phone);
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Inject
        public void setStorage(StorageService storage)
        {
            this.storage = storage;
        }

        private String person;
        private String phone;

        private StorageService storage;
    }


    public static class CommandAddBuilder implements CommandBuilder
    {
        public CommandAddBuilder(Injector injector)
        {
            this.injector = injector;
        }

        @Override
        public Command createCommand(Params params)
        {
            String[] args = null;

            if (StringUtils.isNotEmpty(params.getCommandArgs()))
                args = StringUtils.split(params.getCommandArgs());

            if (args == null || args.length != 2)
                return UnknownCommand.getInstance();

            Command add = new CommandAdd(args[0], args[1]);
            injector.injectMembers(add);

            return add;

        }

        private Injector injector;
    }

    public static class CommandListBuilder implements CommandBuilder
    {
        public CommandListBuilder(Injector injector)
        {
            this.injector = injector;
        }

        @Override
        public Command createCommand(Params params)
        {
            return injector.getInstance(CommandList.class);
        }

        private Injector injector;
    }

    public static class CommandList implements Command
    {
        public static final String NAME = "list";

        @Override
        public void execute(Book model, ApplicationContext ap)
        {
            List<Person> persons = storage.list(model);

            for (Person p : persons)
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

        @Inject
        public void setStorage(StorageService storage)
        {
            this.storage = storage;
        }

        private StorageService storage;
    }
    public static class CommandDeleteBuilder implements CommandBuilder
    {
        @Override
        public Command createCommand(Params params)
        {
            String[] args = null;

            if (StringUtils.isNotEmpty(params.getCommandArgs()))
                args = StringUtils.split(params.getCommandArgs());

            if (args == null)
                return UnknownCommand.getInstance();
            return new CommandDelete(args[0]);
        }
    }

    public static class CommandDelete implements Command
    {
        public static final String NAME = "del";
        public CommandDelete(String person)
        {
            this.person = person;
        }
        @Override
        public void execute(Book model, ApplicationContext ap)
        {
            Set<Person> sp = model.getPersons();
            for (Person p : sp)
                if (this.person.equals(p.getName())) {
                    sp.remove(p);
                    System.out.println("Person:"+ this.person+" deleted!");
                    return;
                }
            System.out.println("Does not found person:"+ this.person+"!");


        }

        @Override
        public String getName() {
            return NAME;
        }


        private String person;
    }
    public static class CommandUpdateBuilder implements CommandBuilder
    {
        @Override
        public Command createCommand(Params params)
        {
            String[] args = null;

            if (StringUtils.isNotEmpty(params.getCommandArgs()))
                args = StringUtils.split(params.getCommandArgs());

            if (args == null|| args.length<2)
                return UnknownCommand.getInstance();
            return new CommandUpdate(args[0], args[1]);
        }
    }

    public static class CommandUpdate implements Command
    {
        public static final String NAME = "update";
        public CommandUpdate(String person, String phone)
        {
            this.person = person;
            this.phone = phone;
        }


        @Override
        public void execute(Book model, ApplicationContext ap)
        {
            Set<Person> sp = model.getPersons();

            for (Person p : sp)
                if (this.person.equals(p.getName())) {
                    p.getPhones().add(new Phone(p, this.phone));
                        System.out.println("Person:"+ this.person+" updated by phone:"+this.phone);
                    return;
                }
            System.out.println("Does not found person:"+ this.person+"!");


        }


        @Override
        public String getName() {
            return NAME;
        }


        private String person;
        private String phone;
    }
    public static class CommandExit implements Command, CommandBuilder
    {
        public static final String NAME = "exit";

        @Override
        public void execute(Book model,  ApplicationContext ap)
        {
            ap.exit();


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
