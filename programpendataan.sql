-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 15, 2017 at 05:06 AM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `programpendataan`
--

-- --------------------------------------------------------

--
-- Table structure for table `anggota`
--

CREATE TABLE `anggota` (
  `id` int(11) NOT NULL,
  `noinduk` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `urlfoto` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `anggota`
--

INSERT INTO `anggota` (`id`, `noinduk`, `nama`, `urlfoto`) VALUES
(1, 5555, 'Erwin Kurniawan Adidharma', ''),
(2, 4444, 'Audi Pratama', '');

-- --------------------------------------------------------

--
-- Table structure for table `kegiatan`
--

CREATE TABLE `kegiatan` (
  `id` int(11) NOT NULL,
  `id_anggota` int(11) NOT NULL,
  `kegiatan` varchar(1000) NOT NULL,
  `tanggal` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kegiatan`
--

INSERT INTO `kegiatan` (`id`, `id_anggota`, `kegiatan`, `tanggal`) VALUES
(1, 1, 'UVUVWEVWEVWE ONYETENYEVWE UGWEMUBWEM OSSAS', '12-02-2017'),
(2, 1, 'IKAN TONGKOL', '12-02-2017'),
(3, 4444, 'AKU TAK BIASA', '13-02-2017'),
(4, 2, 'GALAW', '13-02-2017'),
(5, 2, ' GALAO ', '13-02-2017');

-- --------------------------------------------------------

--
-- Table structure for table `log`
--

CREATE TABLE `log` (
  `id` int(11) NOT NULL,
  `id_anggota` int(11) NOT NULL,
  `event` varchar(50) NOT NULL,
  `waktu` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `log`
--

INSERT INTO `log` (`id`, `id_anggota`, `event`, `waktu`) VALUES
(2, 1, 'Sukses Login ke server', '10-23-2017 08:23:51'),
(3, 1, 'Sukses Login ke server', '10-54-2017 10:54:14'),
(4, 1, 'Sukses Login ke server', '10-56-2017 10:56:11'),
(5, 1, 'Sukses Login ke server', '10-57-2017 10:57:18'),
(6, 1, 'Sukses Login ke server', '10-57-2017 10:57:39'),
(7, 2, 'Sukses Login ke server', '12-02-2017 10:24:07'),
(8, 1, 'Sukses Login ke server', '12-02-2017 10:24:18'),
(9, 1, 'Sukses Login ke server', '12-02-2017 10:24:28'),
(10, 1, 'Sukses Login ke server', '12-02-2017 10:24:45'),
(11, 2, 'Sukses Login ke server', '12-02-2017 10:52:48'),
(12, 1, 'Sukses Login ke server', '12-02-2017 10:54:58'),
(13, 1, 'Sukses Login ke server', '12-02-2017 10:55:12'),
(14, 1, 'Sukses Login ke server', '13-02-2017 07:41:36'),
(15, 1, 'Sukses Login ke server', '13-02-2017 07:41:58'),
(16, 1, 'Sukses Login ke server', '13-02-2017 07:43:16'),
(17, 1, 'Sukses Login ke server', '13-02-2017 07:45:45'),
(18, 1, 'Sukses Login ke server', '13-02-2017 07:46:22'),
(19, 1, 'Sukses Login ke server', '13-02-2017 07:46:34'),
(20, 1, 'Sukses Login ke server', '13-02-2017 07:47:51'),
(21, 1, 'Sukses Login ke server', '13-02-2017 07:48:42'),
(22, 1, 'Sukses Login ke server', '15-02-2017 10:52:07'),
(23, 1, 'Sukses Login ke server', '15-02-2017 10:52:24'),
(24, 1, 'Sukses Login ke server', '15-02-2017 10:54:55');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `status` int(11) NOT NULL,
  `hakspesial` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id`, `username`, `password`, `status`, `hakspesial`) VALUES
(1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 1, 0),
(2, 'member', 'aa08769cdcb26674c6706093503ff0a3', 0, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `anggota`
--
ALTER TABLE `anggota`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `kegiatan`
--
ALTER TABLE `kegiatan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `log`
--
ALTER TABLE `log`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `anggota`
--
ALTER TABLE `anggota`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `kegiatan`
--
ALTER TABLE `kegiatan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `log`
--
ALTER TABLE `log`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
