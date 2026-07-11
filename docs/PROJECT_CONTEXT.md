# HotelCore Project Context

## Project Overview

HotelCore adalah aplikasi Hotel Property Management System (PMS) yang dikembangkan sebagai proyek pembelajaran sekaligus portofolio.

Tujuan utama proyek ini adalah membangun sistem hotel yang mengikuti proses bisnis nyata dengan menjadikan Opera PMS sebagai referensi utama.

---

## Technology Stack

Backend
- Java 21
- Spring Boot
- Spring Data JPA
- Maven

Database
- MySQL

Development Environment
- Ubuntu
- IntelliJ IDEA
- VS Code

Version Control
- Git
- GitHub

---

## Current Progress

Completed

- Development environment
- Git repository
- Database schema
- ERD
- Project documentation

In Progress

- Backend development using Spring Boot

---

## Database (Version 1)

Current entities

- RoomType
- Room
- Rate
- Guest
- User
- Reservation
- Payment

---

## Design Decisions

Current decisions

- One Reservation = One Room
- One Reservation = Many Payments
- Opera PMS is the primary business reference
- Database designed for Version 1 (MVP)

Deferred to Version 2

- Membership
- Split Payment
- Dynamic Pricing
- Housekeeping Module
- Invoice Module
- Promotion Engine

---

## Development Philosophy

- Build small features incrementally.
- Keep commits small and meaningful.
- Follow software engineering best practices.
- Prioritize maintainability over premature complexity.