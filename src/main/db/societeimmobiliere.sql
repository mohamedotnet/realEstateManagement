-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Ven 16 Mars 2018 à 00:54
-- Version du serveur :  10.1.21-MariaDB
-- Version de PHP :  7.0.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `societeimmobiliere`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `sex` varchar(10) NOT NULL,
  `birthday` date NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `address` varchar(200) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `id_number` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `admin`
--

INSERT INTO `admin` (`id`, `name`, `last_name`, `username`, `sex`, `birthday`, `email`, `password`, `address`, `phone`, `id_number`) VALUES
(11, 'ihab', 'benamer', 'overFlow', 'male', '3896-11-11', 'ihab@mail.com', '1234', 'counrty, city , street 201', '12345679', '123456'),
(12, 'john', 'deo', 'admin1', 'male', '1990-01-01', 'john@mail.com', '123', 'paris, france', '0123456789', '123456');

-- --------------------------------------------------------

--
-- Structure de la table `agent`
--

CREATE TABLE `agent` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `sex` varchar(10) NOT NULL,
  `birthday` date NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `address` varchar(200) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `id_number` varchar(20) NOT NULL,
  `nbr_affectation` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `agent`
--

INSERT INTO `agent` (`id`, `name`, `last_name`, `username`, `sex`, `birthday`, `email`, `password`, `address`, `phone`, `id_number`, `nbr_affectation`) VALUES
(1, 'john', 'deo', 'agent1', 'male', '1990-01-01', 'john@mail.com', '123', 'ny, usa', '0123456789', '123', 0),
(2, 'john', 'deo', 'agent2', 'male', '1990-01-01', 'john@mail.com', '123', 'paris, france', '0123456789', '123456', 0);

-- --------------------------------------------------------

--
-- Structure de la table `apartment`
--

CREATE TABLE `apartment` (
  `id` int(11) NOT NULL,
  `reference` varchar(50) NOT NULL,
  `type` varchar(20) NOT NULL,
  `floor` int(3) NOT NULL,
  `id_locality` int(11) NOT NULL,
  `id_building` int(11) NOT NULL,
  `price` int(20) NOT NULL,
  `nbr_room` int(2) NOT NULL,
  `nbr_balcony` int(2) NOT NULL,
  `surface` int(3) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `apartment`
--

INSERT INTO `apartment` (`id`, `reference`, `type`, `floor`, `id_locality`, `id_building`, `price`, `nbr_room`, `nbr_balcony`, `surface`, `status`) VALUES
(3, 'A001', 'F5', 5, 3, 2, 1000000, 5, 3, 120, 0);

-- --------------------------------------------------------

--
-- Structure de la table `appointment`
--

CREATE TABLE `appointment` (
  `id` int(11) NOT NULL,
  `reference` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `id_customer` int(11) NOT NULL,
  `id_agent` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `building`
--

CREATE TABLE `building` (
  `id` int(11) NOT NULL,
  `reference` varchar(50) NOT NULL,
  `nbr_floor` int(11) NOT NULL,
  `id_locality` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `building`
--

INSERT INTO `building` (`id`, `reference`, `nbr_floor`, `id_locality`) VALUES
(2, 'BAT1', 8, 6);

-- --------------------------------------------------------

--
-- Structure de la table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `sex` varchar(10) NOT NULL,
  `birthday` date NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `address` varchar(200) NOT NULL,
  `id_number` varchar(20) NOT NULL,
  `is_activated` tinyint(1) NOT NULL DEFAULT '0',
  `is_banned` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Contenu de la table `customer`
--

INSERT INTO `customer` (`id`, `name`, `last_name`, `username`, `sex`, `birthday`, `email`, `password`, `phone`, `address`, `id_number`, `is_activated`, `is_banned`) VALUES
(3, 'ihab', 'benamer', 'username', 'male', '3896-11-11', 'ihab@mail.com', '123', '0131256', 'constantine, algeria', '122', 0, 0),
(4, 'hello', 'world', 'hello', 'male', '1996-10-11', 'hello@world.com', '123', '0123456789', 'planet earth', '03', 0, 0),
(5, 'john', 'deo', 'customer1', 'male', '1990-01-01', 'john@mail.com', '123', '0123456789', 'paris, france', '123456', 0, 0),
(6, 'john', 'deo', 'customer2', 'male', '1990-01-01', 'john@mail.com', '123', '0123456789', 'cns, dz', '123456', 0, 0);

-- --------------------------------------------------------

--
-- Structure de la table `locality`
--

CREATE TABLE `locality` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(200) NOT NULL,
  `nbr_building` int(3) NOT NULL,
  `nbr_parking` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `locality`
--

INSERT INTO `locality` (`id`, `name`, `address`, `nbr_building`, `nbr_parking`) VALUES
(3, '', 'Wall', 5, 5),
(6, 'L1', 'Adress{country=\'dz\', city=\'cns\', street=\'nvl\'}', 5, 5),
(21, 'L1njdk', 'Adress{country=\'dz\', city=\'cns\', street=\'nvl\'}', 5, 5),
(23, 'L1njdk', 'Adress{country=\'dz\', city=\'cns\', street=\'nvl\'}', 5, 5),
(24, 'L1njdk', 'Adress{country=\'dz\', city=\'cns\', street=\'nvl\'}', 5, 5),
(25, 'loc1', 'cns, dz', 8, 3);

-- --------------------------------------------------------

--
-- Structure de la table `manager`
--

CREATE TABLE `manager` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `sex` varchar(10) NOT NULL,
  `birthday` date NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `address` varchar(200) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `id_number` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Contenu de la table `manager`
--

INSERT INTO `manager` (`id`, `name`, `last_name`, `username`, `sex`, `birthday`, `email`, `password`, `address`, `phone`, `id_number`) VALUES
(1, 'john', 'deo', 'manager1', 'male', '1990-01-01', 'john@mail.com', '123', 'paris, france', '0123456789', '123456');

-- --------------------------------------------------------

--
-- Structure de la table `operator`
--

CREATE TABLE `operator` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `sex` varchar(10) NOT NULL,
  `birthday` date NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `address` varchar(200) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `id_number` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `operator`
--

INSERT INTO `operator` (`id`, `name`, `last_name`, `username`, `sex`, `birthday`, `email`, `password`, `address`, `phone`, `id_number`) VALUES
(1, 'john', 'deo', 'operator1', 'male', '1990-01-01', 'john@mail.com', '123', 'paris, france', '0123456789', '123456');

-- --------------------------------------------------------

--
-- Structure de la table `payment_receipt`
--

CREATE TABLE `payment_receipt` (
  `id` int(11) NOT NULL,
  `value` int(20) NOT NULL,
  `banc` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `id_customer` int(11) NOT NULL,
  `id_sale` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Structure de la table `report`
--

CREATE TABLE `report` (
  `id` int(11) NOT NULL,
  `type` varchar(20) NOT NULL,
  `date` date NOT NULL,
  `description` longtext NOT NULL,
  `id_appointment` int(11) NOT NULL,
  `id_agent` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `sale`
--

CREATE TABLE `sale` (
  `id` int(11) NOT NULL,
  `reference` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `id_customer` int(11) NOT NULL,
  `id_apartment` int(11) NOT NULL,
  `is_validated` tinyint(1) NOT NULL DEFAULT '0',
  `id_payment_receipt` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Index pour la table `agent`
--
ALTER TABLE `agent`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `id` (`id`);

--
-- Index pour la table `apartment`
--
ALTER TABLE `apartment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `locality` (`id_locality`),
  ADD KEY `locality_2` (`id_locality`),
  ADD KEY `id` (`id`),
  ADD KEY `id_building` (`id_building`);

--
-- Index pour la table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_client` (`id_customer`),
  ADD KEY `id_agent` (`id_agent`);

--
-- Index pour la table `building`
--
ALTER TABLE `building`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_locality` (`id_locality`);

--
-- Index pour la table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `id` (`id`);

--
-- Index pour la table `locality`
--
ALTER TABLE `locality`
  ADD PRIMARY KEY (`id`),
  ADD KEY `name` (`name`);

--
-- Index pour la table `manager`
--
ALTER TABLE `manager`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Index pour la table `operator`
--
ALTER TABLE `operator`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Index pour la table `payment_receipt`
--
ALTER TABLE `payment_receipt`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_client` (`id_customer`),
  ADD KEY `id_sell` (`id_sale`);

--
-- Index pour la table `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_agent` (`id_agent`),
  ADD KEY `report_ibfk_2` (`id_appointment`);

--
-- Index pour la table `sale`
--
ALTER TABLE `sale`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`),
  ADD KEY `id_client` (`id_customer`),
  ADD KEY `id_apartment` (`id_apartment`),
  ADD KEY `sale_ibfk_3` (`id_payment_receipt`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT pour la table `agent`
--
ALTER TABLE `agent`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `apartment`
--
ALTER TABLE `apartment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `appointment`
--
ALTER TABLE `appointment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `building`
--
ALTER TABLE `building`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT pour la table `locality`
--
ALTER TABLE `locality`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT pour la table `manager`
--
ALTER TABLE `manager`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `operator`
--
ALTER TABLE `operator`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `payment_receipt`
--
ALTER TABLE `payment_receipt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `report`
--
ALTER TABLE `report`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `sale`
--
ALTER TABLE `sale`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `apartment`
--
ALTER TABLE `apartment`
  ADD CONSTRAINT `apartment_ibfk_1` FOREIGN KEY (`id_locality`) REFERENCES `locality` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `apartment_ibfk_2` FOREIGN KEY (`id_building`) REFERENCES `building` (`id`);

--
-- Contraintes pour la table `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `appointment_ibfk_1` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `appointment_ibfk_2` FOREIGN KEY (`id_agent`) REFERENCES `agent` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `building`
--
ALTER TABLE `building`
  ADD CONSTRAINT `building_ibfk_1` FOREIGN KEY (`id_locality`) REFERENCES `locality` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `payment_receipt`
--
ALTER TABLE `payment_receipt`
  ADD CONSTRAINT `payment_receipt_ibfk_1` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `payment_receipt_ibfk_2` FOREIGN KEY (`id_sale`) REFERENCES `sale` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `report`
--
ALTER TABLE `report`
  ADD CONSTRAINT `report_ibfk_1` FOREIGN KEY (`id_agent`) REFERENCES `agent` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `report_ibfk_2` FOREIGN KEY (`id_appointment`) REFERENCES `appointment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `sale`
--
ALTER TABLE `sale`
  ADD CONSTRAINT `sale_ibfk_1` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sale_ibfk_2` FOREIGN KEY (`id_apartment`) REFERENCES `apartment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sale_ibfk_3` FOREIGN KEY (`id_payment_receipt`) REFERENCES `payment_receipt` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
