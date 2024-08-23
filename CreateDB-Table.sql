DROP DATABASE IF EXISTS apartment_manager
GO

CREATE DATABASE apartment_manager
GO

USE apartment_manager
GO

CREATE TABLE departments (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(255) NOT NULL,
)
GO

CREATE TABLE staffs (
    id VARCHAR(20) PRIMARY KEY,
    name NVARCHAR(255) NOT NULL,
    gender BIT,
    address NVARCHAR(255),
    phone_number VARCHAR(20),
    dob DATE,
    email VARCHAR(255) UNIQUE,
	image VARCHAR(255),
    id_number VARCHAR(20) UNIQUE,
    department_id INT NOT NULL,
    FOREIGN KEY (department_id) REFERENCES departments(id)
)
GO

CREATE TABLE users (
    id VARCHAR(20) PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
	FOREIGN KEY (id) REFERENCES staffs(id),
)
GO

CREATE TABLE households (
    id_number VARCHAR(20) PRIMARY KEY,
	household_head_name NVARCHAR(255) NOT NULL,
	phone_number VARCHAR(20) NOT NULL,
	date_of_birth DATE,
	email VARCHAR(255),
	image VARCHAR(255),
	gender VARCHAR(10),
	member_quantity INT
)
GO

CREATE TABLE apartments_types (
    id INT IDENTITY(1,1) PRIMARY KEY,
    type NVARCHAR(255) NOT NULL,
	price DECIMAL(10, 2) NOT NULL,
)
GO

CREATE TABLE apartments (
    id VARCHAR(20) PRIMARY KEY,
    notes TEXT,
    area_name VARCHAR(20) NOT NULL,
    household_id VARCHAR(20),
    type_id INT NOT NULL,
    status VARCHAR(20) NOT NULL,
	FOREIGN KEY (type_id ) REFERENCES apartments_types(id)
)
GO

CREATE TABLE contracts (
    id INT IDENTITY(1,1) PRIMARY KEY,
    contract_number VARCHAR(20) UNIQUE NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    apartment_id VARCHAR(20) NOT NULL,
    staff_id VARCHAR(20) NOT NULL,
    household_id_number VARCHAR(20) NOT NULL,
	status BIT NOT NULL,
    FOREIGN KEY (apartment_id) REFERENCES apartments(id),
    FOREIGN KEY (staff_id) REFERENCES staffs(id),
    FOREIGN KEY (household_id_number) REFERENCES households(id_number)
)
GO


CREATE TABLE services (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
)
GO

CREATE TABLE service_invoice (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    staff_id VARCHAR(20) NOT NULL,
    printing_date DATE NOT NULL,
	payment_date DATE,
    apartment_id VARCHAR(20) NOT NULL,
    notes TEXT,
    service_id INT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
	status BIT NOT NULL,
    FOREIGN KEY (staff_id) REFERENCES staffs(id),
    FOREIGN KEY (apartment_id) REFERENCES apartments(id),
    FOREIGN KEY (service_id) REFERENCES services(id),
)
GO

CREATE TABLE data_chart (
    id INT IDENTITY(1,1) PRIMARY KEY,
    record_date DATE NOT NULL,
    occupancy_ratio DECIMAL(5, 2) NOT NULL
)
GO

-- Tạo biến để lưu trữ ngày bắt đầu và kết thúc của tháng
DECLARE @StartDate DATE = '2022-01-01';
DECLARE @EndDate DATE = '2023-12-31';

-- Lặp qua từng ngày trong khoảng thời gian và chèn dữ liệu ngẫu nhiên vào bảng
WHILE @StartDate <= @EndDate
BEGIN
    -- Tạo giá trị ngẫu nhiên cho occupancy_ratio trong khoảng từ 70 đến 100
    DECLARE @OccupancyRatio DECIMAL(5, 2) = (RAND() * 30 + 70);

    -- Chèn dữ liệu vào bảng
    INSERT INTO data_chart (record_date, occupancy_ratio)
    VALUES (@StartDate, @OccupancyRatio);

    -- Tăng ngày lên 1 để di chuyển đến ngày tiếp theo
    SET @StartDate = DATEADD(DAY, 1, @StartDate);
END


-- Thêm dữ liệu cho bảng departments
INSERT INTO departments (name) VALUES 
    ('Department A'),
    ('Department B'),
    ('Department C')
	GO

-- Thêm dữ liệu cho bảng staffs
INSERT INTO staffs (id, name, gender, address, phone_number, dob, email, image, id_number, department_id) VALUES
    ('STAFF001', 'Staff A', 1, 'Address A', '1234567890', '1990-01-01', 'staffA@email.com', 'imageA.jpg', 'ID123', 1),
    ('STAFF002', 'Staff B', 0, 'Address B', '9876543210', '1995-05-15', 'staffB@email.com', 'imageB.jpg', 'ID456', 2),
    ('STAFF003', 'Staff C', 1, 'Address C', '5551234567', '1985-08-20', 'staffC@email.com', 'imageC.jpg', 'ID789', 3),
    ('STAFF004', 'Staff D', 0, 'Address D', '3339998888', '1992-12-10', 'staffD@email.com', 'imageD.jpg', 'ID101', 1),
    ('STAFF005', 'Staff E', 1, 'Address E', '7772221111', '1988-03-25', 'staffE@email.com', 'imageE.jpg', 'ID111', 2),
    ('STAFF006', 'Staff F', 0, 'Address F', '4446667777', '1997-07-05', 'staffF@email.com', 'imageF.jpg', 'ID121', 3)
	GO

-- Thêm dữ liệu cho bảng users
INSERT INTO users (id, username, password, role) VALUES
    ('STAFF001', 'userA', 'passwordA', 'admin'),
    ('STAFF002', 'userB', 'passwordB', 'user'),
    ('STAFF003', 'userC', 'passwordC', 'user'),
    ('STAFF004', 'userD', 'passwordD', 'admin'),
    ('STAFF005', 'userE', 'passwordE', 'user'),
    ('STAFF006', 'userF', 'passwordF', 'user')
	GO

-- Thêm dữ liệu cho bảng households
INSERT INTO households (id_number, household_head_name, phone_number, date_of_birth, email, image, gender, member_quantity) VALUES
    ('HH001', 'Head A', '1112223333', '1970-05-10', 'headA@email.com', 'imageHeadA.jpg', 'Male', 4),
    ('HH002', 'Head B', '4445556666', '1982-08-15', 'headB@email.com', 'imageHeadB.jpg', 'Female', 3),
    ('HH003', 'Head C', '7778889999', '1995-02-20', 'headC@email.com', 'imageHeadC.jpg', 'Male', 5)
	GO

-- Thêm dữ liệu cho bảng apartments_types
INSERT INTO apartments_types (type, price) VALUES
    ('Type A', 1500000.00),
    ('Type B', 2000000.00),
    ('Type C', 1800000.00)
	GO

-- Thêm dữ liệu cho bảng apartments
INSERT INTO apartments (id, notes, area_name, type_id, status) VALUES
    ('APT001', 'Notes A', 'Area X', 1, 'Vacant'),
    ('APT002', 'Notes B', 'Area Y', 2, 'Vacant'),
    ('APT003', 'Notes C', 'Area Z', 3, 'Vacant'),
    ('APT004', 'Notes D', 'Area X', 1, 'Vacant'),
    ('APT005', 'Notes E', 'Area Y', 2, 'Vacant')
	GO

-- Thêm dữ liệu cho bảng services
INSERT INTO services (name, price) VALUES
    ('Service A', 100000.00),
    ('Service B', 150000.00),
    ('Service C', 120000.00)
	GO

-- Thêm dữ liệu cho bảng service_invoice
INSERT INTO service_invoice (name, staff_id, printing_date, apartment_id, notes, service_id, quantity, price, status) VALUES
    ('Invoice A', 'STAFF001', '2024-01-10', 'APT001', 'Service A Description', 1, 2, 200000.00, 0),
    ('Invoice B', 'STAFF003', '2024-02-15', 'APT002', 'Service B Description', 2, 1, 150000.00, 0),
    ('Invoice C', 'STAFF002', '2024-03-20', 'APT003', 'Service C Description', 3, 3, 360000.00, 0),
    ('Invoice D', 'STAFF001', '2024-01-12', 'APT004', 'Service A Description', 1, 1, 100000.00, 0),
    ('Invoice E', 'STAFF002', '2024-02-18', 'APT005', 'Service B Description', 2, 2, 300000.00, 0)
	GO


-- SERVICE
CREATE PROCEDURE getAllServices
AS
BEGIN
    SELECT * FROM services;
END
GO

CREATE PROCEDURE addService
    @serviceName VARCHAR(255),
    @servicePrice DECIMAL(10, 2)
AS
BEGIN
    INSERT INTO services (name, price) VALUES (@serviceName, @servicePrice);
END
GO

CREATE PROCEDURE deleteService
    @serviceId INT
AS
BEGIN
    DELETE FROM services WHERE id = @serviceId;
END
GO

CREATE PROCEDURE updateService
    @serviceId INT,
    @newName VARCHAR(255),
    @newPrice DECIMAL(10, 2)
AS
BEGIN
    UPDATE services
    SET name = @newName,
        price = @newPrice
    WHERE id = @serviceId;
END
GO

CREATE PROCEDURE getServiceNameById
    @id INT
AS
BEGIN
    SELECT name
    FROM services
    WHERE id = @id;
END
GO

CREATE PROCEDURE getServiceById
    @id INT
AS
BEGIN
    SELECT *
    FROM services
    WHERE id = @id;
END
GO

CREATE PROCEDURE getAllServiceIds
AS
BEGIN
    SELECT id FROM services
	ORDER BY id;
END
GO

-- SERVICE INVOICE
CREATE PROCEDURE getAllServiceInvoices
AS
BEGIN
    SELECT * FROM service_invoice;
END
GO

CREATE PROCEDURE addServiceInvoice
    @Name VARCHAR(255),
    @StaffId VARCHAR(20),
    @PrintingDate DATE,
	@PaymentDate DATE,
    @ApartmentId VARCHAR(20),
    @Notes TEXT,
    @ServiceId INT,
    @Quantity INT,
    @Price DECIMAL(10, 2),
    @Status BIT
AS
BEGIN
    INSERT INTO service_invoice (name, staff_id, printing_date, payment_date, apartment_id, notes, service_id, quantity, price, status)
    VALUES (@Name, @StaffId, @PrintingDate, @PaymentDate, @ApartmentId, @Notes, @ServiceId, @Quantity, @Price, @Status);
END
GO

CREATE PROCEDURE deleteServiceInvoice
    @id INT
AS
BEGIN
    DELETE FROM service_invoice
    WHERE id = @id;
END
GO

CREATE PROCEDURE updateServiceInvoice
    @Id INT,
    @Name VARCHAR(255),
    @StaffId VARCHAR(20),
    @PrintingDate DATE,
	@PaymentDate DATE,
    @ApartmentId VARCHAR(20),
    @Notes TEXT,
    @ServiceId INT,
    @Quantity INT,
    @Price DECIMAL(10, 2),
    @Status BIT
AS
BEGIN
    UPDATE service_invoice
    SET name = @Name,
        staff_id = @StaffId,
        printing_date = @PrintingDate,
		payment_date = @PaymentDate,
        apartment_id = @ApartmentId,
        notes = @Notes,
        service_id = @ServiceId,
        quantity = @Quantity,
        price = @Price,
        status = @Status
    WHERE id = @Id;
END
GO

CREATE PROCEDURE getServiceInvoiceById
    @id INT
AS
BEGIN
    SELECT *
    FROM service_invoice
    WHERE id = @id;
END
GO

CREATE PROCEDURE setStatusToTrue
    @invoiceId INT,
	@PaymentDate DATE
AS
BEGIN
    UPDATE service_invoice
    SET status = 1,
		payment_date = @PaymentDate
    WHERE id = @invoiceId;
END
GO

CREATE PROCEDURE countInvoice
	@serviceId INT = -1,
    @apartmentId VARCHAR(20) = NULL,
    @staffId VARCHAR(20) = NULL,
	@fromDate DATE = NULL,
    @toDate DATE = NULL
AS
BEGIN
	SELECT COUNT(id) total FROM service_invoice
	WHERE (@serviceId = -1 OR service_id = @serviceId)
      AND (@apartmentId IS NULL OR apartment_id = @apartmentId)
      AND (@staffId IS NULL OR staff_id = @staffId)
	  AND (@fromDate IS NULL OR printing_date >= @fromDate)
      AND (@toDate IS NULL OR printing_date <= @toDate)
END
GO

CREATE PROC getServiceInvoice
	@pageNumber INT,
	@rowOfPage INT,
	@serviceId INT = -1,
    @apartmentId VARCHAR(20) = NULL,
    @staffId VARCHAR(20) = NULL,
	@fromDate DATE = NULL,
    @toDate DATE = NULL
AS
BEGIN
	SELECT * FROM service_invoice
	WHERE (@serviceId = -1 OR service_id = @serviceId)
      AND (@apartmentId IS NULL OR apartment_id = @apartmentId)
      AND (@staffId IS NULL OR staff_id = @staffId)
	  AND (@fromDate IS NULL OR printing_date >= @fromDate)
      AND (@toDate IS NULL OR printing_date <= @toDate)
	ORDER BY id
	OFFSET (@pageNumber-1)*@rowOfPage ROWS
	FETCH NEXT @rowOfPage ROWS ONLY
END
GO

CREATE PROCEDURE getServiceInvoicesByApartmentId
    @ApartmentId VARCHAR(20)
AS
BEGIN
    SELECT
        id,
        name,
        printing_date,
		payment_date,
        notes,
        service_id,
        quantity,
        price,
		status
    FROM
        service_invoice
    WHERE
        apartment_id = @ApartmentId;
END
GO


-- DEPARTMENTS
CREATE PROCEDURE getAllDepartments
AS
BEGIN
    SELECT * FROM departments;
END
GO

CREATE PROCEDURE addDepartment
    @departmentName NVARCHAR(255)
AS
BEGIN
    INSERT INTO departments (name) VALUES (@departmentName);
END
GO

CREATE PROCEDURE deleteDepartment
    @departmentId INT
AS
BEGIN
    DELETE FROM departments WHERE id = @departmentId;
END
GO

CREATE PROCEDURE updateDepartment
    @departmentId INT,
    @newName NVARCHAR(255)
AS
BEGIN
    UPDATE departments
    SET name = @newName
    WHERE id = @departmentId;
END
GO

CREATE PROCEDURE getDepartmentNameById
    @id INT
AS
BEGIN
    SELECT name
    FROM departments
    WHERE id = @id;
END
GO

CREATE PROCEDURE getDepartmentById
    @id INT
AS
BEGIN
    SELECT *
    FROM departments
    WHERE id = @id;
END
GO

CREATE PROCEDURE getAllDepartmentIds
AS
BEGIN
    SELECT id FROM departments
    ORDER BY id;
END
GO

-- STAFFS
CREATE PROCEDURE getAllStaffs
AS
BEGIN
    SELECT * FROM staffs;
END
GO

CREATE PROCEDURE addStaff
    @staffId VARCHAR(20),
    @staffName NVARCHAR(255),
    @gender BIT,
    @address NVARCHAR(255),
    @phoneNumber VARCHAR(20),
    @dob DATE,
    @email VARCHAR(255),
    @image VARCHAR(255),
    @idNumber VARCHAR(20),
    @departmentId INT
AS
BEGIN
    INSERT INTO staffs (id, name, gender, address, phone_number, dob, email, image, id_number, department_id)
    VALUES (@staffId, @staffName, @gender, @address, @phoneNumber, @dob, @email, @image, @idNumber, @departmentId);
END
GO

CREATE PROCEDURE deleteStaff
    @staffId VARCHAR(20)
AS
BEGIN
    DELETE FROM staffs WHERE id = @staffId;
END
GO

CREATE PROCEDURE updateStaff
    @staffId VARCHAR(20),
    @newName NVARCHAR(255),
    @newGender BIT,
    @newAddress NVARCHAR(255),
    @newPhoneNumber VARCHAR(20),
    @newDob DATE,
    @newEmail VARCHAR(255),
    @newImage VARCHAR(255),
    @newIdNumber VARCHAR(20),
    @newDepartmentId INT
AS
BEGIN
    UPDATE staffs
    SET name = @newName,
        gender = @newGender,
        address = @newAddress,
        phone_number = @newPhoneNumber,
        dob = @newDob,
        email = @newEmail,
        image = @newImage,
        id_number = @newIdNumber,
        department_id = @newDepartmentId
    WHERE id = @staffId;
END
GO

CREATE PROCEDURE getStaffNameById
    @id VARCHAR(20)
AS
BEGIN
    SELECT name
    FROM staffs
    WHERE id = @id;
END
GO

CREATE PROCEDURE getStaffById
    @id VARCHAR(20)
AS
BEGIN
    SELECT *
    FROM staffs
    WHERE id = @id;
END
GO

CREATE PROCEDURE getAllStaffIds
AS
BEGIN
    SELECT id FROM staffs
    ORDER BY id;
END
GO

CREATE PROCEDURE GetStaffsWithoutUser
AS
BEGIN
    SELECT s.*
    FROM staffs s
    LEFT JOIN users u ON s.id = u.id
    WHERE u.id IS NULL;
END
GO

-- USERS
CREATE PROCEDURE getAllUsers
AS
BEGIN
    SELECT * FROM users;
END
GO

CREATE PROCEDURE addUser
    @userId VARCHAR(20),
    @username VARCHAR(255),
    @password VARCHAR(255),
    @role VARCHAR(50)
AS
BEGIN
    INSERT INTO users (id, username, password, role)
    VALUES (@userId, @username, @password, @role);
END
GO

CREATE PROCEDURE deleteUser
    @userId VARCHAR(20)
AS
BEGIN
    DELETE FROM users WHERE id = @userId;
END
GO

CREATE PROCEDURE updateUser
    @userId VARCHAR(20),
    @newUsername VARCHAR(255),
    @newPassword VARCHAR(255),
    @newRole VARCHAR(50)
AS
BEGIN
    UPDATE users
    SET username = @newUsername,
        password = @newPassword,
        role = @newRole
    WHERE id = @userId;
END
GO

CREATE PROCEDURE getUsernameById
    @id VARCHAR(20)
AS
BEGIN
    SELECT username
    FROM users
    WHERE id = @id;
END
GO

CREATE PROCEDURE getUserById
    @id VARCHAR(20)
AS
BEGIN
    SELECT *
    FROM users
    WHERE id = @id;
END
GO

CREATE PROCEDURE getAllUserIds
AS
BEGIN
    SELECT id FROM users
    ORDER BY id;
END
GO

CREATE PROCEDURE getUserByUsername
    @username NVARCHAR(255)
AS
BEGIN
    SELECT 
        id, 
        username, 
        password, 
        role
    FROM 
        users
    WHERE 
        username = @username;
END
GO

CREATE PROCEDURE changePassword
    @userId VARCHAR(20),
    @newPassword VARCHAR(255)
AS
BEGIN
    UPDATE users
    SET password = @newPassword
    WHERE id = @userId;
END
GO

-- HOUSEHOLDS
CREATE PROCEDURE getAllHouseholds
AS
BEGIN
    SELECT * FROM households;
END
GO

CREATE PROCEDURE addHousehold
    @idNumber VARCHAR(20),
    @headName NVARCHAR(255),
    @phoneNumber VARCHAR(20),
    @dob DATE,
    @email VARCHAR(255),
    @image VARCHAR(255),
    @gender VARCHAR(10),
    @memberQuantity INT
AS
BEGIN
    INSERT INTO households (id_number, household_head_name, phone_number, date_of_birth, email, image, gender, member_quantity)
    VALUES (@idNumber, @headName, @phoneNumber, @dob, @email, @image, @gender, @memberQuantity);
END
GO

CREATE PROCEDURE deleteHousehold
    @idNumber VARCHAR(20)
AS
BEGIN
    DELETE FROM households WHERE id_number = @idNumber;
END
GO

CREATE PROCEDURE updateHousehold
    @idNumber VARCHAR(20),
    @newHeadName NVARCHAR(255),
    @newPhoneNumber VARCHAR(20),
    @newDob DATE,
    @newEmail VARCHAR(255),
    @newImage VARCHAR(255),
    @newGender VARCHAR(10),
    @newMemberQuantity INT
AS
BEGIN
    UPDATE households
    SET household_head_name = @newHeadName,
        phone_number = @newPhoneNumber,
        date_of_birth = @newDob,
        email = @newEmail,
        image = @newImage,
        gender = @newGender,
        member_quantity = @newMemberQuantity
    WHERE id_number = @idNumber;
END
GO

CREATE PROCEDURE getHouseholdHeadNameById
    @idNumber VARCHAR(20)
AS
BEGIN
    SELECT household_head_name
    FROM households
    WHERE id_number = @idNumber;
END
GO

CREATE PROCEDURE getHouseholdById
    @idNumber VARCHAR(20)
AS
BEGIN
    SELECT *
    FROM households
    WHERE id_number = @idNumber;
END
GO

CREATE PROCEDURE getAllHouseholdIds
AS
BEGIN
    SELECT id_number FROM households
    ORDER BY id_number;
END
GO

-- APARTMENTS_TYPES
CREATE PROCEDURE getAllApartmentTypes
AS
BEGIN
    SELECT * FROM apartments_types;
END
GO

CREATE PROCEDURE addApartmentType
    @typeName NVARCHAR(255),
    @price DECIMAL(10, 2)
AS
BEGIN
    INSERT INTO apartments_types (type, price)
    VALUES (@typeName, @price);
END
GO

CREATE PROCEDURE deleteApartmentType
    @typeId INT
AS
BEGIN
    DELETE FROM apartments_types WHERE id = @typeId;
END
GO

CREATE PROCEDURE updateApartmentType
    @typeId INT,
    @newTypeName NVARCHAR(255),
    @newPrice DECIMAL(10, 2)
AS
BEGIN
    UPDATE apartments_types
    SET type = @newTypeName,
        price = @newPrice
    WHERE id = @typeId;
END
GO

CREATE PROCEDURE getApartmentTypeById
    @id INT
AS
BEGIN
    SELECT *
    FROM apartments_types
    WHERE id = @id;
END
GO

CREATE PROCEDURE getAllApartmentTypeIds
AS
BEGIN
    SELECT id FROM apartments_types
    ORDER BY id;
END
GO

-- APARTMENTS
CREATE PROCEDURE getAllApartments
AS
BEGIN
    SELECT * FROM apartments;
END
GO

CREATE PROCEDURE addApartment
    @apartmentId VARCHAR(20),
    @notes TEXT,
    @areaName VARCHAR(20),
    @householdId VARCHAR(20),
    @typeId INT,
    @status VARCHAR(20)
AS
BEGIN
    INSERT INTO apartments (id, notes, area_name, household_id, type_id, status)
    VALUES (@apartmentId, @notes, @areaName, @householdId, @typeId, @status);
END
GO

CREATE PROCEDURE deleteApartment
    @apartmentId VARCHAR(20)
AS
BEGIN
    DELETE FROM apartments WHERE id = @apartmentId;
END
GO

CREATE PROCEDURE updateApartment
    @apartmentId VARCHAR(20),
    @newNotes TEXT,
    @newAreaName VARCHAR(20),
    @newHouseholdId VARCHAR(20),
    @newTypeId INT,
    @newStatus VARCHAR(20)
AS
BEGIN
    UPDATE apartments
    SET notes = @newNotes,
        area_name = @newAreaName,
        household_id = @newHouseholdId,
        type_id = @newTypeId,
        status = @newStatus
    WHERE id = @apartmentId;
END
GO

CREATE PROCEDURE getApartmentById
    @apartmentId VARCHAR(20)
AS
BEGIN
    SELECT *
    FROM apartments
    WHERE id = @apartmentId;
END
GO

CREATE PROCEDURE getAllApartmentIds
AS
BEGIN
    SELECT id FROM apartments
    ORDER BY id;
END
GO

CREATE PROCEDURE getAreaNameById
    @id VARCHAR(20)
AS
BEGIN
    SELECT area_name
    FROM apartments
    WHERE id = @id;
END
GO

CREATE PROCEDURE GetVacantApartments
AS
BEGIN
    SELECT * 
    FROM apartments
    WHERE status = 'Vacant';
END
GO

CREATE PROCEDURE GetOccupiedApartments
AS
BEGIN
    SELECT * 
    FROM apartments
    WHERE status = 'Occupied';
END
GO

CREATE PROCEDURE GetOccupancyRatio
AS
BEGIN
    DECLARE @OccupiedCount INT;
    DECLARE @TotalCount INT;
    DECLARE @OccupancyRatio DECIMAL(5, 2);

    SELECT @OccupiedCount = COUNT(*) FROM apartments WHERE status = 'Occupied';
    SELECT @TotalCount = COUNT(*) FROM apartments;

    IF @TotalCount = 0
        SET @OccupancyRatio = 0.0; -- To handle division by zero
    ELSE
        SET @OccupancyRatio = CONVERT(DECIMAL(5, 2), @OccupiedCount) / @TotalCount * 100;

    SELECT 
        @OccupiedCount AS OccupiedCount,
        @TotalCount AS TotalCount,
        @OccupancyRatio AS OccupancyRatio;
END
GO

CREATE PROCEDURE UpdateApartmentStatus
    @ApartmentId VARCHAR(20),
	@newHouseholdId VARCHAR(20)
AS
BEGIN
    UPDATE apartments
    SET status = 'Occupied',
		household_id = @newHouseholdId
    WHERE id = @ApartmentId;
END
GO

CREATE PROCEDURE UpdateApartmentStatusToVacant
    @ApartmentId VARCHAR(20)
AS
BEGIN
    UPDATE apartments
    SET status = 'Vacant',
		household_id = null
    WHERE id = @ApartmentId;
END
GO

-- CONTRACTS
CREATE PROCEDURE getAllContracts
AS
BEGIN
    SELECT * FROM contracts;
END
GO

CREATE PROCEDURE getValidatedContract
AS
BEGIN
    SELECT *
    FROM contracts
    WHERE status = 1;
END
GO

CREATE PROCEDURE addContract
    @contractNumber VARCHAR(20),
    @startDate DATE,
    @endDate DATE,
    @apartmentId VARCHAR(20),
    @staffId VARCHAR(20),
    @householdIdNumber VARCHAR(20),
    @status BIT
AS
BEGIN
    INSERT INTO contracts (contract_number, start_date, end_date, apartment_id, staff_id, household_id_number, status)
    VALUES (@contractNumber, @startDate, @endDate, @apartmentId, @staffId, @householdIdNumber, @status);
END
GO

CREATE PROCEDURE deleteContract
    @contractId INT
AS
BEGIN
    DELETE FROM contracts WHERE id = @contractId;
END
GO

CREATE PROCEDURE updateContract
    @contractId INT,
    @newContractNumber VARCHAR(20),
    @newStartDate DATE,
    @newEndDate DATE,
    @newApartmentId VARCHAR(20),
    @newStaffId VARCHAR(20),
    @newHouseholdIdNumber VARCHAR(20),
    @newStatus BIT
AS
BEGIN
    UPDATE contracts
    SET contract_number = @newContractNumber,
        start_date = @newStartDate,
        end_date = @newEndDate,
        apartment_id = @newApartmentId,
        staff_id = @newStaffId,
        household_id_number = @newHouseholdIdNumber,
        status = @newStatus
    WHERE id = @contractId;
END
GO

CREATE PROCEDURE getContractById
    @contractId INT
AS
BEGIN
    SELECT *
    FROM contracts
    WHERE id = @contractId;
END
GO

CREATE PROCEDURE getAllContractIds
AS
BEGIN
    SELECT id FROM contracts
    ORDER BY id;
END
GO

CREATE PROCEDURE UpdateContractStatusToFalse
    @ContractId INT
AS
BEGIN
    UPDATE contracts
    SET status = 0
    WHERE id = @ContractId;
END
GO

--Data Chart
CREATE PROCEDURE InsertDataChartRow
    @RecordDate DATE,
    @OccupancyRatio DECIMAL(5, 2)
AS
BEGIN
    INSERT INTO data_chart (record_date, occupancy_ratio)
    VALUES (@RecordDate, @OccupancyRatio);
END
GO

CREATE PROCEDURE GetOccupancyRatioByYearMonth
    @Year INT,
    @Month INT
AS
BEGIN
    DECLARE @StartDate DATE = DATEFROMPARTS(@Year, @Month, 1);
    DECLARE @EndDate DATE = EOMONTH(@StartDate);

    SELECT record_date, occupancy_ratio
    FROM data_chart
    WHERE record_date >= @StartDate AND record_date <= @EndDate;
END
GO


