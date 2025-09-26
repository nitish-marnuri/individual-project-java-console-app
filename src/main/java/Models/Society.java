package Models;

/*
The Society class is an abstract base implementation of the ISociety interface
serving as a base class for different types of societies
so that any specific society class can be extended from it ensuring extensibility and maintainability.
Examples for extensible classes: University Society, Business Society, Research Society.

This class follows the SOLID design principles
allowing the high-level modules focus on ISociety interface providing flexibility
and open to extension by specific society types without modifying this class directly.
*/

public abstract class Society implements ISociety{

    // Protected member variables for society properties.
    protected int id; // Unique identifier for the society

    protected String name; // Name of the society

    protected String description; // Description of the society's purpose or activities

    // Gets the unique identifier of the society.
    public int getId() { return id; }

    // Gets the name of the society.
    public String getName() { return name; }

    // Gets the description of the society.
    public String getDescription() { return description; }

    // Sets the unique identifier of the society.
    public void setId(int id) { this.id = id; }

    // Sets the name of the society.
    public void setName(String name) { this.name = name; }

    // Sets the description of the society.
    public void setDescription(String description) { this.description = description; }
}
