-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 19, 2023 at 01:49 AM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 7.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cangkir`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `UserID` char(5) NOT NULL,
  `CupID` char(5) NOT NULL,
  `Quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `mscourier`
--

CREATE TABLE `mscourier` (
  `CourierID` char(5) DEFAULT NULL,
  `CourierName` varchar(30) DEFAULT NULL,
  `CourierPrice` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mscourier`
--

INSERT INTO `mscourier` (`CourierID`, `CourierName`, `CourierPrice`) VALUES
('CO001', 'JNE', 2000),
('CO002', 'JNT', 3000),
('CU003', 'Lion Parcel', 5000),
('CU004', 'Ninja', 9000);

-- --------------------------------------------------------

--
-- Table structure for table `mscup`
--

CREATE TABLE `mscup` (
  `CupID` char(5) NOT NULL,
  `CupName` varchar(60) DEFAULT NULL,
  `CupPrice` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mscup`
--

INSERT INTO `mscup` (`CupID`, `CupName`, `CupPrice`) VALUES
('CU001', 'Blob Cup', 120000);

-- --------------------------------------------------------

--
-- Table structure for table `msuser`
--

CREATE TABLE `msuser` (
  `UserID` char(5) NOT NULL,
  `Username` varchar(60) DEFAULT NULL,
  `UserEmail` varchar(60) DEFAULT NULL,
  `UserPassword` varchar(17) DEFAULT NULL,
  `UserGender` varchar(10) DEFAULT NULL,
  `UserRole` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `msuser`
--

INSERT INTO `msuser` (`UserID`, `Username`, `UserEmail`, `UserPassword`, `UserGender`, `UserRole`) VALUES
('US001', 'vanesBenedictus', 'vanesbenedictus@gmail.com', 'vanes123', 'Male', 'Admin'),
('US002', 'mario123', 'mario@gmail.com', 'mario123', 'Male', 'User'),
('US003', 'beni123', 'yoyo@gmail.com', 'beno123', 'Male', 'User'),
('US004', 'ahmad99', 'hero@gmail.com', 'bintang123', 'Male', 'User'),
('US005', 'jovito', 'jovitogunawan29@gmail.com', 'jovito123', 'Male', 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `transactiondetail`
--

CREATE TABLE `transactiondetail` (
  `TransactionID` char(5) NOT NULL,
  `CupID` char(5) NOT NULL,
  `Quantity` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transactiondetail`
--

INSERT INTO `transactiondetail` (`TransactionID`, `CupID`, `Quantity`) VALUES
('TR001', 'CU001', 1),
('TR001', 'CU002', 7),
('TR002', 'CU001', 1),
('TR002', 'CU003', 1),
('TR003', 'CU001', 1),
('TR003', 'CU003', 3),
('TR004', 'CU001', 1),
('TR004', 'CU002', 1),
('TR005', 'CU001', 1),
('TR005', 'CU002', 1),
('TR005', 'CU003', 1),
('TR006', 'CU002', 5),
('TR006', 'CU003', 3),
('TR007', 'CU001', 11),
('TR007', 'CU002', 11),
('TR007', 'CU003', 11),
('TR008', 'CU001', 1),
('TR008', 'CU002', 3),
('TR009', 'CU001', 1),
('TR009', 'CU003', 1),
('TR010', 'CU001', 1),
('TR011', 'CU001', 4),
('TR011', 'CU002', 8),
('TR011', 'CU003', 1),
('TR012', 'CU001', 9),
('TR012', 'CU002', 13),
('TR013', 'CU002', 8),
('TR014', 'CU001', 1),
('TR014', 'CU002', 2),
('TR014', 'CU003', 6),
('TR015', 'CU004', 5);

-- --------------------------------------------------------

--
-- Table structure for table `transactionheader`
--

CREATE TABLE `transactionheader` (
  `TransactionID` char(5) NOT NULL,
  `UserID` char(5) DEFAULT NULL,
  `CourierID` char(5) DEFAULT NULL,
  `TransactionDate` date DEFAULT NULL,
  `UseDeliveryInsurance` binary(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transactionheader`
--

INSERT INTO `transactionheader` (`TransactionID`, `UserID`, `CourierID`, `TransactionDate`, `UseDeliveryInsurance`) VALUES
('TR001', 'US002', 'CO001', '2023-12-08', 0x31),
('TR002', 'US002', 'CO001', '2023-12-09', 0x31),
('TR003', 'US003', 'CU003', '2023-12-09', 0x31),
('TR004', 'US002', 'CO001', '2023-12-09', 0x31),
('TR005', 'US002', 'CO001', '2023-12-09', 0x31),
('TR006', 'US002', 'CU003', '2023-12-09', 0x31),
('TR007', 'US002', 'CO001', '2023-12-09', 0x31),
('TR008', 'US002', 'CO001', '2023-12-10', 0x31),
('TR009', 'US003', 'CO001', '2023-12-12', 0x31),
('TR010', 'US002', 'CO001', '2023-12-12', 0x31),
('TR011', 'US002', 'CO001', '2023-12-13', 0x31),
('TR012', 'US002', 'CO001', '2023-12-13', 0x31),
('TR013', 'US003', 'CO001', '2023-12-13', 0x31),
('TR014', 'US002', 'CO001', '2023-12-15', 0x31),
('TR015', 'US002', 'CO001', '2023-12-15', 0x31);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`UserID`,`CupID`),
  ADD KEY `CupID` (`CupID`);

--
-- Indexes for table `mscourier`
--
ALTER TABLE `mscourier`
  ADD UNIQUE KEY `CourierName` (`CourierName`);

--
-- Indexes for table `mscup`
--
ALTER TABLE `mscup`
  ADD PRIMARY KEY (`CupID`),
  ADD UNIQUE KEY `CupName` (`CupName`);

--
-- Indexes for table `msuser`
--
ALTER TABLE `msuser`
  ADD PRIMARY KEY (`UserID`),
  ADD UNIQUE KEY `Username` (`Username`),
  ADD UNIQUE KEY `UserEmail` (`UserEmail`);

--
-- Indexes for table `transactiondetail`
--
ALTER TABLE `transactiondetail`
  ADD PRIMARY KEY (`TransactionID`,`CupID`);

--
-- Indexes for table `transactionheader`
--
ALTER TABLE `transactionheader`
  ADD PRIMARY KEY (`TransactionID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `msuser` (`UserID`),
  ADD CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`CupID`) REFERENCES `mscup` (`CupID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
