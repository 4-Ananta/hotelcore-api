# Architecture Decisions


## 2026-07-23

### Decision
Payment amount is entered manually by the client, not auto-filled from Reservation's totalAmount.

### Reason
Supports partial payments (deposit, installment) consistent with the One Reservation = Many Payments design.

---


## 2026-07-21

### Decision
Relations (Room→RoomType, Rate→RoomType, Reservation→Guest/Room/Rate/User, Payment→Reservation/User) are looked up via human-readable unique fields (name, roomNumber, identityNumber, username) instead of raw database id, in Request DTOs.

### Reason
More descriptive and testable API requests; client doesn't need to know internal ids.

---

## 2026-07-11

### Decision

Reservation has a One-to-Many relationship with Payment.

### Reason

Support:

- Deposit
- Full Payment
- Extend Stay
- Corporate Invoice

### Alternative

Reservation → One Payment

### Rejected Because

Cannot support multiple payment transactions.

---

## 2026-07-11

### Decision

Use Opera PMS as the primary business reference.

### Reason

The project is intended to follow real hotel business processes.

---

## 2026-07-11

### Decision

One Reservation only books one Room.

### Reason

Keep Version 1 simple.

### Future Consideration

Multiple-room reservation will be added in Version 2.