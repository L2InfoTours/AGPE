-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 05 jan. 2021 à 14:52
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `proj_exam`
--

-- --------------------------------------------------------

--
-- Structure de la table `contraintes`
--

CREATE TABLE `contraintes` (
  `ContrainteID` int(11) NOT NULL,
  `ExamID` int(11) NOT NULL,
  `ContrainteType` int(11) NOT NULL COMMENT 'Type de contrainte\r\n0:salle(ROOM_EXCLUSIVE)\r\n1: examen(AFTER)\r\n2: examen(EXAM_COINCIDENCE)\r\n3: examen(EXCLUSION)\r\n4: créneaux\r\n5: élèves',
  `ContrainteArgument` int(11) NOT NULL COMMENT 'Quand 0:idSalle\r\nQuand 1-3:idExam\r\nQuand 4:idCreneaux\r\nQuand 5:idEleve\r\n'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `contraintes`
--

INSERT INTO `contraintes` (`ContrainteID`, `ExamID`, `ContrainteType`, `ContrainteArgument`) VALUES
(380, 1, 5, 1),
(381, 1, 5, 2),
(382, 2, 5, 3),
(383, 2, 5, 4),
(384, 1, 1, 2),
(386, 1, 0, 2),
(387, 1, 2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `creneaux`
--

CREATE TABLE `creneaux` (
  `CreneauxID` int(11) NOT NULL,
  `CreneauxDT` datetime NOT NULL,
  `CreneauxLength` smallint(6) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `creneaux`
--

INSERT INTO `creneaux` (`CreneauxID`, `CreneauxDT`, `CreneauxLength`) VALUES
(1, '2021-01-04 09:30:00', 190),
(2, '2021-01-04 14:00:00', 210),
(3, '2021-01-05 09:30:00', 190),
(13, '2020-05-24 14:00:00', 210);

-- --------------------------------------------------------

--
-- Structure de la table `eleves`
--

CREATE TABLE `eleves` (
  `EleveID` int(11) NOT NULL,
  `EleveNom` text NOT NULL,
  `ElevePrenom` text NOT NULL,
  `EleveNum` char(8) NOT NULL,
  `Promo` varchar(10) NOT NULL,
  `Annee` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `eleves`
--

INSERT INTO `eleves` (`EleveID`, `EleveNom`, `ElevePrenom`, `EleveNum`, `Promo`, `Annee`) VALUES
(4, 'Collot', 'Adrien', '21354879', 'info', 2),
(3, 'Deze', 'Raphael', '21354679', 'info', 2),
(2, 'Mermod', 'Benjamin', '21354579', 'info', 2),
(1, 'Martin', 'Tom', '2190325', 'info', 2);

-- --------------------------------------------------------

--
-- Structure de la table `examen`
--

CREATE TABLE `examen` (
  `ExamenID` int(11) NOT NULL,
  `ExamenTitre` char(100) NOT NULL COMMENT 'Jusqu''à 100 char.',
  `ExamenLength` smallint(6) NOT NULL COMMENT 'En minute',
  `ExamenMat` varchar(20) NOT NULL COMMENT 'matière',
  `ExamenType` varchar(10) NOT NULL COMMENT 'oral ou écrit'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `examen`
--

INSERT INTO `examen` (`ExamenID`, `ExamenTitre`, `ExamenLength`, `ExamenMat`, `ExamenType`) VALUES
(1, 'Génie logiciel UML', 120, '', ''),
(3, 'Mathématiques calculus', 150, '', ''),
(2, 'informatique programmation java', 120, '', '');

-- --------------------------------------------------------

--
-- Structure de la table `liaison`
--

CREATE TABLE `liaison` (
  `ExamenID` int(11) NOT NULL,
  `CreneauxID` int(11) NOT NULL,
  `SallesID` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `liaison`
--

INSERT INTO `liaison` (`ExamenID`, `CreneauxID`, `SallesID`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3);

-- --------------------------------------------------------

--
-- Structure de la table `login`
--

CREATE TABLE `login` (
  `LoginID` int(11) NOT NULL,
  `LoginUser` tinytext NOT NULL,
  `LoginPass` text NOT NULL COMMENT 'Hash en SHA256 ou MD5',
  `LoginStatus` tinyint(4) NOT NULL COMMENT '1 =secretariat\r\n2 =scolarité \r\n3= admin',
  `LoginMail` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `login`
--

INSERT INTO `login` (`LoginID`, `LoginUser`, `LoginPass`, `LoginStatus`, `LoginMail`) VALUES
(1, 'Tom', 'deusvult', 3, 'tom.martin@gmail.com'),
(2, 'secretariat', 'secretariat', 1, 'secretariat@gmail.com'),
(3, 'scolarite', 'scolarite', 2, 'scolarite@gmail.com');

-- --------------------------------------------------------

--
-- Structure de la table `salles`
--

CREATE TABLE `salles` (
  `SallesID` int(11) NOT NULL,
  `SallesNom` text NOT NULL,
  `SallesCapacite` smallint(6) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `salles`
--

INSERT INTO `salles` (`SallesID`, `SallesNom`, `SallesCapacite`) VALUES
(3, 'L 50', 50),
(2, 'F 100', 50),
(1, 'E 100', 100);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `contraintes`
--
ALTER TABLE `contraintes`
  ADD PRIMARY KEY (`ContrainteID`);

--
-- Index pour la table `creneaux`
--
ALTER TABLE `creneaux`
  ADD PRIMARY KEY (`CreneauxID`);

--
-- Index pour la table `eleves`
--
ALTER TABLE `eleves`
  ADD PRIMARY KEY (`EleveID`);

--
-- Index pour la table `examen`
--
ALTER TABLE `examen`
  ADD PRIMARY KEY (`ExamenID`);

--
-- Index pour la table `liaison`
--
ALTER TABLE `liaison`
  ADD PRIMARY KEY (`ExamenID`);

--
-- Index pour la table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`LoginID`);

--
-- Index pour la table `salles`
--
ALTER TABLE `salles`
  ADD PRIMARY KEY (`SallesID`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `contraintes`
--
ALTER TABLE `contraintes`
  MODIFY `ContrainteID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=388;

--
-- AUTO_INCREMENT pour la table `creneaux`
--
ALTER TABLE `creneaux`
  MODIFY `CreneauxID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT pour la table `eleves`
--
ALTER TABLE `eleves`
  MODIFY `EleveID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=263;

--
-- AUTO_INCREMENT pour la table `examen`
--
ALTER TABLE `examen`
  MODIFY `ExamenID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=126;

--
-- AUTO_INCREMENT pour la table `login`
--
ALTER TABLE `login`
  MODIFY `LoginID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `salles`
--
ALTER TABLE `salles`
  MODIFY `SallesID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

-- Mote

CREATE TABLE `matiere` (
  `ID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `Name` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- default content

INSERT INTO `matiere` (`ID`, `Name`) VALUES
(0,"IDB"),
(1,"JAVA"),
(2,"GL");

CREATE TABLE `materiel` (
  `ID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `Name` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- default content

INSERT INTO `materiel` (`ID`, `Name`) VALUES
(NULL,"PC"),
(NULL,"TP"),
(NULL,"Amphi");
  
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
