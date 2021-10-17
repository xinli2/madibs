# madibs

Project Description

For this project, we will use the Java Collections framework to define a custom collection that is a Map. To test it, you will write a word Madlibs-esque word puzzle which relies on your Map structure to function. This game should first be written using the Java HashMap collection, but once your ListMap has been successfully implemented and tested, you will replace every instance of HashMap in your Madlibs game with an instance of your ListMap

Example Run
Enter a(n) Adjective: interesting
Enter a(n) Noun: frog
Enter a(n) Plural Noun: dogs

There are many (INTERESTING) ways to choose a/an (FROG) to read. First, you
could ask for recommendations from your friends and (DOGS).

Puzzle complete! Would you like to play again?

Implementation

For the implementation of Madlibs, you are to read in a file of Madlibs templates, with one template per line, named templates.txt. Randomly select one of the templates from the file each run to begin the game.

You will also need to read a file of words and their associated parts of speech, also provided as parts_of_speech.txt. Create a HashMap to store this file as well. Keep in mind that a word such as 鈥渄og鈥� may count as both a noun (N) and a verb (V).

If a user tries to replace a template mapping with a word not in the Map, or a word that doesn鈥檛 have the appropriate part of speech, *do something reasonable*.

Part Of Speech Tags

These are the tags (in the templates) and what they mean for the user

| Tag | English Part of Speech | |---------|----------------------------| | ADJ | Adjective | | N | Noun | | PLN | Plural Noun | | GER | Verb Ending In 鈥�-ing鈥� | | VPT | Verb Past Tense | | V | Verb | | PN | Proper Noun | | PPN | Plural Proper Noun | | AA | Article Adjective | | NUM | Number |

Hint

To help us do the replacement, we will use a powerful text matching feature called 鈥渞egular expressions鈥�. Here鈥檚 some sample code that you should run and understand that may be adaptable to your program.
//This string holds the template with parenthesized, all-caps placeholders
String in = "My friend, (GEORGE), was an (AMAZING) baseball player. ";

//This constructs a regular expression: a pattern that represents some text.
//The pattern we have here expresses the pattern: left parenthesis, one or more
//capital letters in a row, and a right parenthesis.
//The syntax looks frightening because parentheses have special meanings in
//regular expressions, so we need to escape them, and it's a Java String, so
//we need to escape the backslash to escape the parentheses.
Pattern p = Pattern.compile("\\\\([A-Z]+\\\\)");

//We can then use a Matcher object to find matches in a source string
Matcher m = p.matcher(in);

//We will capture the string after each replacement is made into "out"
String out = null;

//We will replace the two words in our template with these words instead:
String[] replacements = {"LINDA", "OUTSTANDING"};
int i = 0;
//While there is a match
while (m.find()) {

    //m.group() is the leftmost match for our pattern's text
    System.*out*.println("Replacing: " + m.group() + " with " + replacements[i]);
    
    //We can replace the first match with our replacement text.
    out = m.replaceFirst(replacements[i++]);
    
    //We now want to perform matches on the string after replacement, so we
    //need to apply the pattern to the return value of our replace code for
    //the next iteration
    m = p.matcher(out);

}

System.out.println("Final string: " + out);

MVC

You are required to create the game portion of the project using the MVC architectural pattern. You must have the following 3 classes:

1.聽聽聽聽聽聽聽Madlibs聽鈥� This is the main class. When run, the game will begin,similarly to how a new game of Mastermind was played in the previousproject.

2.聽聽聽聽聽聽聽MadlibsController聽鈥� This class contains all of the stringreplacement logic, all methods must be useful to the view, but notactually contain any code that prints to the console.

3.聽聽聽聽聽聽聽MadlibsModel聽鈥� This class contains all of the data used torepresent the Madlibs game. In specific, you will need to store the Map ofempty spaces (and their according part of speech) to words. 聽

ListMap

In the first version of your Madlibs program, the Map you used was Java's HashMap. However, for this project, you will replace that with a map that you create according to the rules of the java.util Framework.

A ListMap will be a map (dictionary) that is implemented using a linked list. Each node will have two Object fields, one for the key and one for the value. It will also have any references to other nodes necessary to construct the list. Create the node as a private inner class of the ListMap class. Grow the list as necessary.

Your ListMap will be a templated (generic type) that will allow maps of any type to any other type. It will be declared as:
public class ListMap<K, V> extends AbstractMap<K,V> 

You are required to provide the implementations of several methods to make your map work.
@Override
public V put(K key, V value)

This method adds key and value to your map. If key already exists, the new value replaces the old one, and the old one is returned.
@Override
public int size()

This method returns the number of mappings that the object contains.
@Override
public Set<Entry<K, V>> entrySet()

Returns a Set of key, value pairs contained in an Entry object. The AbstractMap class provides a concrete SimpleEntry class that we can use to hold them.

You will need to provide a concrete set, which you will do via a private inner class:
private class ListMapEntrySet extends AbstractSet<Entry<K,V>>

You will need to implement these methods in ListMapEntrySet:
@Override
public int size()

This method returns the size of the set (and of the Map).
@Override
public boolean contains(Object o)

This method should return true if the Set contains an Entry equal to the the one represented by the parameter. If o is not an Entry, this is trivially false. If it is, validate that the key and the value are actually part of the Map.
@Override
public Iterator<Entry<K,V>> iterator() 

This returns an iterator that walks over the Set of Entries in the Map. This iterator will also be implemented as a private inner class:
private class ListMapEntrySetIterator<T> implements Iterator<T>

And must provide implementations of:
@Override
public boolean hasNext()

Returns true if there are more items in the Set of Entries being iterated over.
@Override
public T next() 

Returns an Entry (an AbstractMap.SimpleEntry<V,E> for us) that represents the next mapping in our Map.

Using ListMap for Madlibs

Once you've built and tested your ListMap class by itself, you will replace HashMap in your Madlibs project with your ListMap. Use generic Map references and construct new ListMaps. This should require minimal code changes.

Do not assume your ListMap will only be tested against the Cryptogram program. Make sure it works for any reasonable application.

This means that each insert you will need to extend the internal linked list. Make a inner class node type to hold your key, value pairs and necessary pointers. Make sure you have a test suite that exercises your code to convince yourself that it works independently of the Madlibs program.

Requirements
•The ListMap class needs all of the methods and inner classes as explained above
•A reasonable Test Suite that convinces you that your ListMap works (we will grade with our own test suite for correctness, but yours should exist and do what we want our test suite to do).
•Your test suite should reach 100% branch coverage on ListMap, ListMapEntrySet, and ListMapEntrySetIterator
•You may use any of the templated methods provided by AbstractMap or AbstractCollection, and these also will serve as good methods to use in your JUnit tests since they implicitly call the methods you have overridden.
•Documentation using javadoc for each method and class.
