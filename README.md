# FitForLife

**FitForLife** is a simple, friendly Android fitness app designed 
to help users log quick workouts, track fitness activities, and store
short notes about each session. It is suitable for beginners and 
intermediate users looking to start or maintain a fitness routine.

---

## Table of Contents
- [Features](#features)
- [Screenshots](#screenshots)
- [Tech Stack](#tech-stack)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

---
1. I started by creating a simple mockup login screen. I added a sign in with google too.

<img width="720" height="1600" alt="Login_Screen" src="https://github.com/user-attachments/assets/d638f3b9-f02f-490a-87e2-15debe5be050" />


## Features
- User authentication with **Email/Password** and **Google Sign-In** via Firebase Auth.
- Add workout sessions with:
    - Title
    - Type (Cardio, Strength, Flexibility, Mobility, Other)
    - Duration
    - Notes
    - Date/time
- View a list of past workout sessions in descending order (most recent first).
- Edit and delete workout sessions.
- Simple and clean UI designed for quick interactions.

---

## Screenshots
*Add screenshots here to visually showcase your app, e.g., login screen, session list, create session.*

---
<<<<<<< HEAD
-- Added register screen

=======
 -- Added register screen
![WhatsApp Image 2025-11-29 at 23 41 22 (1)](https://github.com/user-attachments/assets/3bcd1f2b-84b5-46be-b455-ee7c00a384e8)
>>>>>>> 1deb2182190e1aae42a8bcb818f2b7e49bfcbfa3

-- I added launcher Icon

 ![WhatsApp Image 2025-11-30 at 11 16 50](https://github.com/user-attachments/assets/6f2f8767-9a87-4b07-bae5-93da8f40178c)

-- integrated with firebase for data storage
<img width="918" height="322" alt="image" src="https://github.com/user-attachments/assets/76cdf0fa-f2b0-4fbb-a978-abd2e7cc8389" />


-- created a session view screen with create session button
    and logout button
    
![createsessionview](https://github.com/user-attachments/assets/9b880ba7-c4f1-4f30-b99b-4d6d3720bab8)

-- Pressing create button takes you to create session screen
    You create session by providing session title, selecting type of 
    session from dropdown, enter duration and some notes about session
![createsession](https://github.com/user-attachments/assets/0d9b3be6-da05-46e0-8ac0-bfb27593880b)


-- You can also save the session. Doing so takes you back to the session
    screen to view your session.
![sessionlist](https://github.com/user-attachments/assets/868be5a0-ca47-4d82-bfaa-8f349cbaedb9)

-- You can also edit your session by clicking on it from the list
![createsession](https://github.com/user-attachments/assets/65a10380-ac91-4d9d-8d9a-04f03d24fe42)


-- Long pressing the session in the list deletes the session
![sessions](https://github.com/user-attachments/assets/0599ef89-a141-424e-adea-8d7cee2b0abf)



## Tech Stack
- **Platform:** Android (Kotlin)
- **Backend:** Firebase Authentication & Firestore Database
- **UI:** Android Jetpack Compose / Material Design Components
- **Libraries:**
    - Firebase BoM
    - Firebase Firestore
    - Firebase Auth
    - Google Sign-In
    - RecyclerView, ConstraintLayout, Material Components

---

## Installation

1. **Clone the repository**
```bash
git clone https://github.com/your-username/FitForLife.git
