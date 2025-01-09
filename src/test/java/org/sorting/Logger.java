package org.sorting;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class Logger
{
    public final List<Entry> log;
    
    
    public Logger()
    {
        this.log = new ArrayList<>();
    }
    
    public void log(int[] feld, int[] sortedFeld, boolean isSorted)
    {
        log.add(new Entry(feld, sortedFeld, isSorted));
    }
    
    public void Write(String path) throws IOException
    {
        var file = new File(path);
        file.delete();
        file.createNewFile();
        var writer = Files.newBufferedWriter(file.toPath());
        
        for (var entry : log)
        {
            writer.write(entry.toString());
            writer.newLine();
        }
        writer.close();
    }
    
    public static Logger Load(String path) throws IOException
    {
        var logger = new Logger();
        var lines = Files.readAllLines(Path.of(path));
        for (var line : lines)
        {
            var split = line.split(";");
            var feld = ArrayParser.parse(split[0]);
            var sortedFeld = ArrayParser.parse(split[1]);
            var isSorted = Boolean.parseBoolean(split[2]);
            logger.log(feld, sortedFeld, isSorted);
        }
        return logger;
    }
    
    
    public record Entry(int[] feld, int[] sortedFeld, boolean failed)
    {
        @Override
        public String toString()
        {
            return String.format("%s;%s;%s", ArrayParser.parse(feld), ArrayParser.parse(sortedFeld), failed);
        }
    }
}
