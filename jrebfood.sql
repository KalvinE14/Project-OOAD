-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 11, 2020 at 04:04 PM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jrebfood`
--

-- --------------------------------------------------------

--
-- Table structure for table `carts`
--

CREATE TABLE `carts` (
  `userId` int(11) NOT NULL,
  `foodId` int(11) NOT NULL,
  `qty` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `employeeId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `dob` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`employeeId`, `roleId`, `name`, `dob`, `email`, `password`, `status`) VALUES
(1, 1, 'Niko Rangel', '2001-08-03', 'niko@gmail.com', 'niko06', 'Active'),
(2, 3, 'Ayaz Cantrell', '1989-12-01', 'ayaz@gmail.com', 'ayaz07', 'Active'),
(3, 1, 'Pranav Buckley', '1999-09-15', 'pranav@gmail.com', 'pranav08', 'Inactive'),
(4, 2, 'Shelbie Bateman', '1995-12-30', 'shelbie@gmail.com', 'shelbie09', 'Active'),
(5, 2, 'Hasnain Arroyo', '2000-02-14', 'hasnain@gmail.com', 'hasnain10', 'Inactive');

-- --------------------------------------------------------

--
-- Table structure for table `foods`
--

CREATE TABLE `foods` (
  `foodId` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `foods`
--

INSERT INTO `foods` (`foodId`, `name`, `price`, `description`, `status`) VALUES
(1, 'Burger', 12000, 'A sandwich consisting of one or more cooked patties of ground meat, usually beef, placed inside a sliced bread roll or bun.', 'Available'),
(2, 'Sandwich', 10000, 'A food typically consisting of vegetables, sliced cheese or meat, placed on or between slices of bread', 'Not Available'),
(3, 'Fried Rice', 20000, 'A dish of cooked rice that has been stir-fried in a wok or a frying pan', 'Available'),
(4, 'Spaghetti', 25000, 'A staple food of traditional Italian cuisine', 'Available'),
(5, 'Fried Chicken', 15000, 'A dish consisting of chicken pieces which have been coated in a seasoned batter and pan-fried', 'Not Available');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `orderId` int(11) NOT NULL,
  `orderDate` date NOT NULL,
  `address` varchar(255) NOT NULL,
  `userId` int(11) NOT NULL,
  `driverId` int(11) DEFAULT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`orderId`, `orderDate`, `address`, `userId`, `driverId`, `status`) VALUES
(1, '2020-11-18', '1476 August Lane', 1, NULL, 'Not accepted'),
(2, '2020-11-17', '1476 August Lane', 1, 1, 'Accepted'),
(3, '2020-11-16', '1476 August Lane', 1, 1, 'Ordered'),
(4, '2020-11-15', '1476 August Lane', 1, 1, 'Cooked'),
(5, '2020-11-14', '1476 August Lane', 1, 1, 'Finished');

-- --------------------------------------------------------

--
-- Table structure for table `order_details`
--

CREATE TABLE `order_details` (
  `orderId` int(11) NOT NULL,
  `foodId` int(11) NOT NULL,
  `qty` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_details`
--

INSERT INTO `order_details` (`orderId`, `foodId`, `qty`) VALUES
(1, 1, 1),
(1, 2, 2),
(2, 5, 4),
(2, 4, 1),
(3, 2, 2),
(3, 5, 4),
(4, 1, 2),
(4, 3, 2),
(5, 1, 1),
(5, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `roleId` int(11) NOT NULL,
  `roleName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`roleId`, `roleName`) VALUES
(1, 'Driver'),
(2, 'Chef'),
(3, 'Manager');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userId` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phoneNumber` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userId`, `name`, `address`, `email`, `phoneNumber`, `password`) VALUES
(1, 'Kye Freeman', '1476 August Lane', 'kye@gmail.com', '+44-735-5520-375', 'kye01'),
(2, 'Gabriella Barker', '2403 Hart Street', 'gabriella@gmail.com', '+44-755-5541-874', 'gabriella02'),
(3, 'Zayden Herrera', '1132 College Street', 'zayden@gmail.com', '+44-785-5519-340', 'zayden03'),
(4, 'Zacharia Devlin', '4668 Stockert Hollow Road', 'zacharia@gmail.com', '+44-725-5559-659', 'zacharia04'),
(5, 'Jan Rhodes', '2724  Hart Street', 'jan@gmail.com', '+44-725-5557-369', 'jan05');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `carts`
--
ALTER TABLE `carts`
  ADD KEY `fk_cart_user` (`userId`),
  ADD KEY `fk_cart_food` (`foodId`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`employeeId`),
  ADD KEY `fk_role` (`roleId`);

--
-- Indexes for table `foods`
--
ALTER TABLE `foods`
  ADD PRIMARY KEY (`foodId`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`orderId`),
  ADD KEY `fk_order_driver` (`driverId`),
  ADD KEY `userId` (`userId`);

--
-- Indexes for table `order_details`
--
ALTER TABLE `order_details`
  ADD KEY `fk_detail_order` (`orderId`),
  ADD KEY `fk_food_order` (`foodId`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`roleId`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `employeeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `foods`
--
ALTER TABLE `foods`
  MODIFY `foodId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `orderId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `roleId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `carts`
--
ALTER TABLE `carts`
  ADD CONSTRAINT `fk_cart_food` FOREIGN KEY (`foodId`) REFERENCES `foods` (`foodId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_cart_user` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `employees`
--
ALTER TABLE `employees`
  ADD CONSTRAINT `fk_role` FOREIGN KEY (`roleId`) REFERENCES `roles` (`roleId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `fk_order_driver` FOREIGN KEY (`driverId`) REFERENCES `employees` (`employeeId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `fk_detail_order` FOREIGN KEY (`orderId`) REFERENCES `orders` (`orderId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_food_order` FOREIGN KEY (`foodId`) REFERENCES `foods` (`foodId`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
