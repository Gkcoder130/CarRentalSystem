# 🚗 Car Rental System (Console-Based)

A simple console-based Java application for managing car rentals, built with:

✅ Core Java (OOP, JDBC, Exception Handling)  
✅ PostgreSQL Database  
✅ Maven Project Structure

---

## 📂 Project Structure

```
CarRentalSystem/
└── src/
    └── main/
        └── java/
            └── com/
                └── carrental/
                    ├── AdminMenu.java         # Admin menu (add/delete cars)
                    ├── UserMenu.java          # User menu (rent/view cars)
                    ├── CarServer.java         # Business logic and DB operations
                    ├── databaseConnection.java
└── pom.xml
```

---

## 🗃️ Database Setup (PostgreSQL)

Before running the app, ensure you have PostgreSQL installed and running.  
Ask your IT team for help if you’re unsure how to do this.

## 💡 How to Run

1. **Open a terminal and go to the project folder**
2. **For Admin Menu:**  
   ```
   mvn compile exec:java -Padmin
   ```
3. **For User Menu:**  
   ```
   mvn compile exec:java -Puser
   ```

---

## 📝 Features

- Add and delete cars (admin)
- View available cars (user)
- Rent a car (user)
- Automatic car availability after rental period ends
- Simple, menu-driven console interface

---

## 🖼️ Example Screenshots / Results

Below are sample outputs from the Car Rental System as seen in the terminal:

**Build Success:**

![Build Success](Screenshot%202025-07-09%20203627.png)

**Admin Menu:**

![Admin Menu](Screenshot%202025-07-09%20203728.png)

**User Menu:**

![User Menu](Screenshot%202025-07-09%20204017.png)

To add your own screenshots, simply take a screenshot of your terminal and save it in the project folder with the names above, or update the links to match your filenames.

---

## 👤 User Instructions

- **Admins:** Use the Admin Menu to add or remove cars.
- **Users/Staff:** Use the User Menu to see available cars and rent a car.
- **Navigation:** Follow on-screen instructions and enter the number for your desired action.

---

## ❓ Troubleshooting

- **Database Connection Error:**  
  Ensure PostgreSQL is running and the connection details in the code match your setup.

- **No Cars Available:**  
  Make sure the admin has added cars to the system.

- **App Won’t Start:**  
  Check that you have Java and Maven installed. Use `java -version` and `mvn -version` to confirm.

---

## 🆘 Support

If you have any questions or issues, please contact your system administrator or IT support team.

---

## 📋 Notes

- All data is safely stored in PostgreSQL.
- You can customize the database connection in `databaseConnection.java` if needed.
- For advanced features or changes, contact your developer or IT support.

---

Thank you for using the Car Rental System!
