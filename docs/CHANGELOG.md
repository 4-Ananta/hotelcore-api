# Changelog

## Version 0.3.0

### Added
- User module (Entity, Repository, DTO, Service, Controller) with BCrypt password hashing
- Rate module with @ManyToOne relation to RoomType (lookup by name)
- Reservation module with 4 relations (Guest, Room, Rate, User), date validation, and automatic calculation of numberOfNights and totalAmount
- Payment module with relations to Reservation and User, manual amount entry to support partial/installment payments

### Fixed
- Added `updatable = false` to `updated_at` column across all entities (Guest, RoomType, Room, User, Rate) — previously stale timestamps were being written back on update, overriding MySQL's automatic `ON UPDATE CURRENT_TIMESTAMP`

### Changed
- Room and Rate modules refactored to look up RoomType by name instead of by id, for more descriptive API requests

## Version 0.2.0

### Added
- Guest module (Entity, Repository, DTO, Service, Controller)
- RoomType module (Entity, Repository, DTO, Service, Controller)
- Room module (Entity, Repository, DTO, Service, Controller) with @ManyToOne relation to RoomType

### Changed
- Migrated Guest module from direct Entity exposure to DTO pattern
- Consolidated database schema.sql (previously incomplete due to overwrites)

## Version 0.1.0

### Added
- Git repository
- Database schema
- ERD
- Project documentation

---

Future releases will be documented here.