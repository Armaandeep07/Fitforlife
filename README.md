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
-- I added launcher Icon

-- Added register screen

-- integrated with firebase for data storage

-- created a session view screen with create session button
    and logout button

-- Pressing create button takes you to create session screen
    You create session by providing session title, selecting type of 
    session from dropdown, enter duration and some notes about session

-- You can also save the session. Doing so takes you back to the session
    screen to view your session.

-- You can also edit your session by clicking on it from the list

-- Long pressing the session in the list deletes the session



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
