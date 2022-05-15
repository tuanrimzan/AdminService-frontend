-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 12, 2022 at 05:21 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electrogriddb`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customerID` char(5) NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customerID`, `name`, `address`) VALUES
('C0001', 'Danuja Wijerathna', 'no 9/A meewathura , Peradeniya');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `empID` char(5) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(20) NOT NULL,
  `empType` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`empID`, `name`, `email`, `empType`) VALUES
('EGEN1', 'Kasun Denipitiya', 'kasun.d@EG.com', 'engineer'),
('EGSP1', 'Sheron weeratunge', 'sheron.w@EG.com', 'support');

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE `task` (
  `taskID` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `description` varchar(200) NOT NULL,
  `customerID` char(5) NOT NULL,
  `handleBy` char(5) NOT NULL,
  `status` varchar(10) NOT NULL DEFAULT 'Pending',
  `createTime` timestamp NOT NULL DEFAULT current_timestamp(),
  `lastUpdate` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`taskID`, `title`, `description`, `customerID`, `handleBy`, `status`, `createTime`, `lastUpdate`) VALUES
(1, 'Power Meter not work properly', 'hello my Power Meter not work properly', 'C0001', 'EGSP1', 'Pending', '2022-04-12 06:06:18', '2022-04-12 06:06:18'),
(2, 'sfds', 'sdfsf', 'C0001', 'EGEN1', 'Pending', '2022-04-12 10:55:06', '2022-04-12 10:55:06');

-- --------------------------------------------------------

--
-- Table structure for table `task_workers`
--

CREATE TABLE `task_workers` (
  `taskID` int(11) NOT NULL,
  `workerID` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `task_workers`
--

INSERT INTO `task_workers` (`taskID`, `workerID`) VALUES
(1, 'EGEN1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customerID`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`empID`);

--
-- Indexes for table `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`taskID`),
  ADD KEY `foreign_key_customer` (`customerID`),
  ADD KEY `foreign_key_employee` (`handleBy`);

--
-- Indexes for table `task_workers`
--
ALTER TABLE `task_workers`
  ADD PRIMARY KEY (`taskID`,`workerID`),
  ADD KEY `foreign_key_worker` (`workerID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `task`
--
ALTER TABLE `task`
  MODIFY `taskID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `foreign_key_customer` FOREIGN KEY (`customerID`) REFERENCES `customer` (`customerID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `foreign_key_employee` FOREIGN KEY (`handleBy`) REFERENCES `employee` (`empID`);

--
-- Constraints for table `task_workers`
--
ALTER TABLE `task_workers`
  ADD CONSTRAINT `foreign_key_task` FOREIGN KEY (`taskID`) REFERENCES `task` (`taskID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `foreign_key_worker` FOREIGN KEY (`workerID`) REFERENCES `employee` (`empID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
