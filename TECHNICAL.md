# Technical Explanation

Oracle has a helpful tutorial, [Creating a GUI With Swing](https://docs.oracle.com/javase/tutorial/uiswing/index.html).  Skip the Learning Swing with the NetBeans IDE section. 

When I create a Swing application, I use the [model-view-controller](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) pattern.  This pattern allows me to separate my concerns and focus on one part of the application at a time.

A Swing application model consists of one or more plain Java getter/setter classes.  The model has no idea that the view or controllers exist.

A Swing view consists of one `JFrame` and as many `JPanels` and `JDialogs` as necessary.

Swing controllers consist of the `Actions` and `Listeners` that modify the contents of the model and update the view.

## Model

The model consists of 14 classes and an `Enum`.  I could not have created this Swing application without a robust application model.

The `RouletteModel` class is the main model class.  All other model classes and enums support this class.  The `RouletteModel` class holds a `java.util.List` of `Player` instances, the selected `Player` instance (for betting), an instance of the `RouletteWheel`, an instance of `RouletteChips`, an instance of (a betting) `Round`, an instance of `ClickableWagers`, and some `int` constants.

The `ChipValue` class holds the `int` value of a chip for a player.

The `ChipValues` class holds all the possible `int` values of a chip.

The `ClickableWager` class holds a `String` array of numbers, an instance of `WagerType`, and a `java.awt.Rectangle` that holds the bounds of the roulette table spot for the `Wager` instance.  As one example, a street bet on the third row holds the number values "7", "8", and "9".  The numbers are `Strings` for the purpose of display on the roulette wheel.

The `ClickableWagers` class holds a `List` of `ClickableWager` instances, a `RouletteWheel` instance, and some `int` constants that define the roulette table drawing area.  There are hundreds of `ClickableWager` instances in the `List`.

The `Player` class holds the `String` name, the `RouletteChip` instance, and the `ChipValue` instance for the player.

The `PlayerWagerError` class is a convenience class that allows me to return an `int` wager error code for a bad wager.

The `RouletteChip` class holds a `java.awt.Color` instance for the chip color and a `Color` instance for the chip accent color.

The `RouletteChips` class holds the eight possible `RouletteChip` color instances.

The `RouletteTableDrawingConstants` class holds several `int` constants that allow me to draw the roulette table and determine where on the roulette table bets are placed.

The `RouletteWheel` class holds the 38 `WheelSegment` instances information of a double zero roulette wheel in clockwise order.

The `Round` class holds a `List` of `Wager` instances.

The `Wager` class holds a `Player` instance, a `WagerType` instance, a `String` array of the wager numbers, and a `java.awt.Point` location on the roulette table where the wager is located.

The `WagerType` enum holds the 17 different types of wagers.  They are arranged in deescending odds order.

The `WheelSegment` class holds a `String` number, a background `Color` instance, and a `Point` correction delta.  The number is a `String` so I can display "00".  The segment number has to be adjusted to fit in the center of the segment, so the correction delta tells me how much to offset the segment number display.

## View

The view is divided into two packages.  One package holds the `JFrame` and associated `JPanels`.  The other package holds the `JDialogs` and associated `JPanels`.  It's a good idea not to put too many classes in a package.

### View - JFrame

The `JFrame` view consists of a `JFrame` class, 7 `JPanel` classes, and 3 image classes.

The `RouletteFrame` class creates the `JFrame` and has several convenience getter/setter methods so that I only have to pass one instance of the `RouletteFrame` class to the controller classes.  The controller classes don't need to know what part of the view they're updating.

The `CallBoardPanel` class creates the call board `JPanel`.  The call board is a customized `JTable`.  This class has a few interal controller classes to manage the appearance of the `JTable`.


The `ControlPanel` class holds the `CallBoardPanel` `JPanel`, the `PlayerPanel` `JPanel`, and the `MenuPanel` `JPanel`.  One way to create complex `JPanel` layouts is to combine simpler `JPanel` layouts.

The `MenuPanel` class holds the menu `JButtons`.  This class uses a `GridBagLayout`.

The `PlayerPanel` class creates the player `JPanel`.  The player `JPanel` uses a `JTable` to hold the `Player` instances.  This class has a few interal controller classes to manage the appearance of the `JTable`.

The `RouletteTablePanel` class holds a drawing `JPanel` where I draw the roulette table.  Study the [Performing Custom Painting](https://docs.oracle.com/javase/tutorial/uiswing/painting/index.html) section to see how this drawing `JPanel` works.

The `RouletteTableWheelPanel`holds the `RouletteWheelPanel` `JPanel` and the `RouletteTablePanel` `JPanel`.

The `RouletteWheelPanel` class holds a drawing `JPanel` where I draw the roulette wheel.

The `ChipImage` class creates a `java.awt.image.BufferedImage` of a chip.

The `RouletteTableImage` class creates a `BufferedImage` of the roulette table.

The `RouletteWheelImage` class creates a `BufferedImage` of the roulette wheel.

### View - JDialogs

The `JDialogs` view consists of 5 `JDialogs` and 4 `JPanels`.

The `AddPlayerDialog` class allows the user to add a `Player` instance.  The `AddPlayerPanel` class creates the `JPanel` for the `JDialog`.

The `BettingDialog` class shows the user the betting odds.  The `BettingPanel` class creates the `JPanel` for the `JDialog`.

The `InstructionsDialog` class shows the user the game instructions.  I copied this class from a different project, so I didn't create a separate `JPanel` for the `JDialog`.

The `RemovePlayerDialog` class allows the user to cash out and end the game.  The `RemovePlayerPanel` class creates the `JPanel` for the `JDialog`.

The `WinningsDialog` class shows the user the players with winning wagers.  The `WinningsPanel` class creates the `JPanel` for the `JDialog`.

## Controller

There are 5 controller classes.

The `AddPlayerListener` class implements an `ActionListener` that's triggered by the "Player Join" `JButton` on the `MenuPanel`.  The `AddPlayerListener` class creates a `Player` instance.

The `ChipListener` class extends a `MouseAdapter` that's triggered by left clicking or right clicking on the `RouletteTablePanel`.

The `PlayerSelectionListener` class implements a `ListSelectionListener` that listens for which `Player` instance in the `PlayerPanel` class is selected.

The `RemovePlayerListener` class implements an `ActionListener` that's triggered by the "Player Cash Out" `JButton` on the `MenuPanel`.  The `RemovePlayerListener` class removes a `Player` instance.

The `WheelSpinListener` class implements an `ActionListener` that's triggered by the "Spin Wheel" `JButton` on the `MenuPanel`.
