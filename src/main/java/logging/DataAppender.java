package logging;

import java.io.Serializable;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractOutputStreamAppender;
import org.apache.logging.log4j.core.appender.FileManager;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.util.Strings;

@Plugin(name = "DataToFileAppender", category = "Core", elementType = "appender", printObject = true)
public class DataAppender extends AbstractOutputStreamAppender<FileManager> {

	private static final int DEFAULT_BUFFER_SIZE = 8192;
	private static final String DEFAULT_FILE_NAME = "logMe/default.log";

	public DataAppender(String name, Layout<? extends Serializable> layout,
			Filter filter, boolean ignoreExceptions, boolean immediateFlush,
			FileManager manager) {
		super(name, layout, filter, ignoreExceptions, immediateFlush, manager);
		// TODO Auto-generated constructor stub
	}

	@PluginFactory
	public static DataAppender createAppender(
			@PluginAttribute("name") String name,
			@PluginAttribute("fileName") String fileNameParam,
			@PluginElement("layout") Layout<? extends Serializable> layout,
			@PluginElement("filter") final Filter filter,
			@PluginElement("fileManager") final FileManager fileManager,
			@PluginAttribute("immediateFlush") final String immediateFlush,
			@PluginAttribute("ignoreExceptions") final String ignore) {
		if (name == null) {
			LOGGER.error("No name provided for DataAppender");
			return null;
		}
		if (layout == null) {
			layout = PatternLayout.createDefaultLayout();
		}

		if (Strings.isEmpty(fileNameParam)) {
			fileNameParam = DEFAULT_FILE_NAME;
		}

		final FileManager manager = FileManager.getFileManager(fileNameParam,
				true, false, false, null, layout, DEFAULT_BUFFER_SIZE, true);

		return new DataAppender(name, layout, filter,
				Boolean.parseBoolean(ignore),
				Boolean.parseBoolean(immediateFlush), manager);

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		super.start();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
	}

	@Override
	public void append(LogEvent event) {
		// TODO Auto-generated method stub
		super.append(event);
	}

}
