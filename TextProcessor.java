
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Davor
 *
 * Klasa ciji su posao operacije nad tekstom.
 */
public final class TextProcessor
{
    private String dictionaryName;
    private int dictionarySize;
    private final List<String> dictionaryData = new LinkedList<String>();

    public TextProcessor(String dictionaryName)
    {
        this.dictionaryName = dictionaryName;
    }

    public void loadDictionary(final MainWindow window)
    {
        new Thread()
        {
            @Override
            public void run()
            {
                window.getProgressBar().setFont(new Font("Monospaced", Font.BOLD, 15));
                window.getProgressBar().setStringPainted(true);

                Scanner input = null;
                try
                {
                    input = new Scanner(new BufferedReader(new FileReader(new File(
                            getClass().getResource(dictionaryName).toURI())), 6000000));

                    dictionarySize = Integer.parseInt(input.nextLine());
                    window.getProgressBar().setMaximum(dictionarySize);

                    int dictionaryRead = 1;
                    while (input.hasNext())
                    {
                        dictionaryData.add(input.nextLine().trim());
                        dictionaryRead++;
                        window.getProgressBar().setValue(dictionaryRead);
                    }
                }
                catch (FileNotFoundException e)
                {
                    String errorText = "Datoteka koja sadrzi recnik nije pronadjena. Program ce "
                            + "sada prekinuti izvrsavanje.";
                    JOptionPane.showMessageDialog(window, errorText,
                                                  "Greska!", JOptionPane.ERROR_MESSAGE);
                    System.out.println("GRESKAAAAAAA!");
                    System.exit(1);
                }
                catch (URISyntaxException ex)
                {
                    String errorText = "Datoteku koja sadrzi recnik nije moguce otvoriti. \n"
                            + "Neispravna konverzija iz URL u URI \n"
                            + "Program ce sada prekinuti izvrsavanje.";
                    JOptionPane.showMessageDialog(window, errorText,
                                                  "Greska!", JOptionPane.ERROR_MESSAGE);
                    System.out.println("GRESKAAAAAAA!");
                    System.exit(1);
                }
                finally
                {
                    input.close();
                }

                window.setInputEnabled(true);
            }
        }.start();
    }

    public void search(final MainWindow window, final String pattern, final int precision)
    {
        new Thread()
        {
            @Override
            public void run()
            {
                window.getProgressBar().setStringPainted(true);
                window.getProgressBar().setString(null);
                window.getProgressBar().setMaximum(dictionarySize);
                window.getDictionaryTableModel().removeAll();
                window.getProgressBar().setValue(1);


                String regexp = ".*";
                if (pattern.length() < precision)
                    regexp += pattern;
                else
                    regexp += pattern.substring(pattern.length() - precision);
                Pattern compiledRegexp = Pattern.compile(regexp);

                for (Iterator<String> it = dictionaryData.iterator(); it.hasNext();)
                {
                    String dictionaryWord = it.next();

                    if (compiledRegexp.matcher(dictionaryWord.toLowerCase()).matches())
                        window.getDictionaryTableModel().appendValue(dictionaryWord);

                    window.getProgressBar().setValue(window.getProgressBar().getValue() + 1);
                }

                window.setInputEnabled(true);
            }
        }.start();
    }
}
