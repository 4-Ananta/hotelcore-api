# Architecture Decisions

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