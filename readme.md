# Online Food Store

## Docker Commands for DB

### Run

```zsh
docker compose up -d
```

### Stop

```zsh
docker compose down
```

## Database Schema

The "Online Food Store" application is designed to manage various aspects of an online food ordering system. Below is a
detailed explanation of the database schema and how the different entities interact with each other.

![DB](https://github.com/user-attachments/assets/2d3596d5-334d-4fcf-95db-53984ade2c13)

### User Management

- **User (`user`)**: This table stores the information of users registered in the system. A user can have one of three
  roles: `CUSTOMER`, `RESTAURANT_OWNER`, or `ADMIN`. Each user has attributes like email, name, password, and role.
- **User Addresses (`user_addresses`)**: A user can register multiple addresses, which are stored in this table. Each
  address is linked to the user through the `user_id` foreign key.

### Restaurant Management

- **Restaurant (`restaurant`)**: This table contains details of restaurants such as email, social media links,
  description, opening hours, address, and owner. The `owner_id` foreign key links to the user who owns the restaurant.
- **Restaurant Images (`restaurant_images`)**: A restaurant can have multiple images associated with it, stored in this
  table.
- **Food Category (`food_category`)**: Restaurant owners can create various food categories (e.g., appetizers, main
  courses) stored in this table.

### Ingredients Management

- **Ingredient Category (`ingredient_category`)**: This table stores different categories of ingredients that can be
  used by restaurants.
- **Ingredients Item (`ingredients_item`)**: Restaurants can list various ingredients they use, which are stored in this
  table and categorized by `ingredient_category_id`.

### Food Management

- **Food (`food`)**: This table stores details of food items available at restaurants, including attributes like
  description, price, availability, and category. Each food item is linked to a specific restaurant and category.
- **Food Images (`food_images`)**: Multiple images can be associated with each food item, stored in this table.

### Cart and Orders

- **Cart (`cart`)**: This table represents a shopping cart for a customer. It stores the total amount and is linked to
  the customer via the `customer_id` foreign key.
- **Cart Item (`cart_item`)**: Each item in a cart is stored in this table, linked to the cart and food item.
- **Orders (`orders`)**: Once a cart is checked out, an order is created. This table stores order details like creation
  date, status, total amount, and links to the customer, restaurant, and delivery address.
- **Order Item (`order_item`)**: Items in an order are stored here, with details about quantity, price, and the
  associated food item.
- **Orders Items (`orders_items`)**: This table links order items to orders through a many-to-many relationship.

### Favorites and Additional Features

- **User Favorites (`user_favorites`)**: Users can mark food items as favorites. This table stores the user’s favorite
  items.
- **Address (`address`)**: This table stores address details and is linked to both users and restaurants.

### Entity Relationships

- **User and User Addresses**: A user can have multiple addresses.
- **User and Restaurant**: A restaurant is owned by a user with the `RESTAURANT_OWNER` role.
- **Restaurant and Food**: A restaurant can offer multiple food items.
- **Food and Food Category**: Food items are categorized into different food categories.
- **Restaurant and Ingredient Category**: Restaurants manage ingredient categories.
- **Ingredient Category and Ingredients Item**: Ingredients are categorized.
- **Food and Ingredients Item**: Each food item can be made from multiple ingredients.
- **Cart and Cart Item**: A cart contains multiple items.
- **Order and Order Item**: An order contains multiple items.
- **User and User Favorites**: Users can have multiple favorite food items.
- **Restaurant and Restaurant Images**: A restaurant can have multiple images.
- **Food and Food Images**: A food item can have multiple images.

This schema ensures a comprehensive and scalable structure for managing users, restaurants, food items, orders, and
other related entities in the "Online Food Store" application.

