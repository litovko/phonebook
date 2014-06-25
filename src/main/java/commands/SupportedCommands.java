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


        register(CommandAdd.NAME,  injector.getInstance(CommandAddBuilder.class));
        register(CommandList.NAME, injector.getInstance(CommandListBuilder.class));
        register(ExitCommand.NAME, injector.getInstance(ExitCommand.class));
    }

    public static class CommandAdd extends AbstractCommand
    {
        public static final String NAME = "add";


        public CommandAdd(String person, String phone, StorageService storage)
        {
            super(storage);
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

        private String person;
        private String phone;

    }


    public static class CommandAddBuilder extends AbstractCommandBuilder {

        @Inject
        public CommandAddBuilder(StorageService storageService)
        {
            super(storageService);
        }

        @Override
        public Command createCommand(Params params)
        {
            String[] args = null;

            if (StringUtils.isNotEmpty(params.getCommandArgs()))
                args = StringUtils.split(params.getCommandArgs());

            if (args == null || args.length != 2)
                return UnknownCommand.getInstance();

            Command add = new CommandAdd(args[0], args[1], service);
            /*add.
            injector.injectMembers(add);*/

            return add;

        }

        //private Injector injector;
    }

    public static class CommandListBuilder extends AbstractCommandBuilder
    {
        @Inject
        public CommandListBuilder(StorageService storageService) {
            super(storageService);

        }

        @Override
        public Command createCommand(Params params)
        {
            return new CommandList(getService());
        }

    }

    public static class CommandList extends AbstractCommand
    {
        public static final String NAME = "list";


        public CommandList(StorageService storage)
        {
            super(storage);
        }

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

    }
}
