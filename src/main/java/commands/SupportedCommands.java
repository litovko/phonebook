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
        Injector injector = Guice.createInjector(new DBModule());


        register(CommandAdd.NAME,  new CommandAddBuilder(injector));
        register(CommandList.NAME, new CommandListBuilder(injector));
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
}
