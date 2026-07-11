# Database Documentation

## Database

hotelcore_db

---

## Current Tables

- room_type
- room
- rate
- guest
- user
- reservation
- payment

---

## Relationships

RoomType
↓

Room

Guest
↓

Reservation

Room
↓

Reservation

Rate
↓

Reservation

Reservation
↓

Payment

User
↓

Reservation

User
↓

Payment

---

## Database Version

Version 1 (MVP)

---

Detailed documentation for each table will be added as backend development progresses.