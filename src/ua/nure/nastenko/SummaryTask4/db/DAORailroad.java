package ua.nure.nastenko.SummaryTask4.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.ArrayAllocationExpression;

import ua.nure.nastenko.SummaryTask4.db.entity.User;
import ua.nure.nastenko.SummaryTask4.db.info.RoadInfo;

public class DAORailroad {

	private static DAORailroad dbManager = null;
	private static Connection con = null;
	private static PreparedStatement stm = null;

	private DAORailroad() {
		con = ConnectionFactory.getConnection();

	}

	public static DAORailroad getDAORailroad() {
		if (dbManager == null) {
			dbManager = new DAORailroad();
		}
		return dbManager;
	}

	public boolean show() {
		try {
			stm = con.prepareStatement("SELECT * FROM railroad.users");

			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " "
						+ rs.getString(3) + " " + rs.getString(4) + " "
						+ rs.getString(5) + " " + rs.getInt(6));
			}
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	public User getUser(String login) {
		try {
			stm = con
					.prepareStatement("SELECT * FROM railroad.users WHERE login = ?");
			stm.setString(1, login);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				User result = new User(rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6));

				return result;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public boolean addTrain(int number) {
		try {
			stm = con
					.prepareStatement("INSERT INTO railroad.train (idtrain) VALUES (?);");// add
																							// train
			stm.setInt(1, number);
			stm.executeUpdate();
			for (int i = 0; i < 10; i++) {
				stm = con
						.prepareStatement("INSERT INTO railroad.wagon (type, wagon_number) VALUES (1, ?);");// add
																											// 10
																											// wagon
				stm.setInt(1, i + 1);
				stm.executeUpdate();
				int[] masWagonsId = new int[10];
				stm = con
						.prepareStatement("SELECT idwagon FROM railroad.wagon order by idwagon desc Limit 1;");// get
																												// ID
																												// of
																												// last
																												// wagon
				ResultSet rs = stm.executeQuery();
				rs.next();
				masWagonsId[i] = rs.getInt(1);
				stm = con
						.prepareStatement("INSERT INTO railroad.t_w (t_id, w_id) VALUES (?, ?);");// connect
																									// train
																									// with
																									// wagons
				stm.setInt(1, number);
				stm.setInt(2, masWagonsId[i]);
				stm.executeUpdate();

				for (int j = 0; j < 48; j++) {
					stm = con
							.prepareStatement("INSERT INTO railroad.places (place_number) VALUES (?);");// add
																										// 48
																										// places
					stm.setInt(1, j + 1);
					stm.executeUpdate();
					int[] masPlacesId = new int[48];
					stm = con
							.prepareStatement("SELECT idplaces FROM railroad.places order by idplaces desc Limit 1;");// get
																														// ID
																														// of
																														// last
																														// place
					rs = stm.executeQuery();
					rs.next();
					masPlacesId[j] = rs.getInt(1);
					stm = con
							.prepareStatement("INSERT INTO railroad.w_p (w_id, p_id) VALUES (?, ?);");// connect
																										// places
																										// with
																										// wagons
					stm.setInt(1, masWagonsId[i]);
					stm.setInt(2, masPlacesId[j]);
					stm.executeUpdate();
				}

			}

			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public boolean deleteTrain(int number) {
		try {

			int[] masWagonId = new int[10];
			int[] masPlaceId = new int[48];
			stm = con
					.prepareStatement("SELECT w_id FROM railroad.t_w  WHERE t_id = ?");
			stm.setInt(1, number);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				for (int i = 0; i < 10; i++) {
					masWagonId[i] = rs.getInt(1);
					stm = con
							.prepareStatement("SELECT p_id FROM railroad.w_p  WHERE w_id = ?");
					stm.setInt(1, masWagonId[i]);
					ResultSet rs2 = stm.executeQuery();
					if (rs2.next()) {
						for (int j = 0; j < 48; j++) {
							masPlaceId[j] = rs2.getInt(1);
							rs2.next();
						}
					}
					stm = con
							.prepareStatement("DELETE FROM railroad.w_p WHERE w_id=?");
					stm.setInt(1, masWagonId[i]);
					stm.executeUpdate();
					for (int j = 0; j < 48; j++) {
						stm = con
								.prepareStatement("DELETE FROM railroad.places WHERE idplaces=?");
						stm.setInt(1, masPlaceId[j]);
						stm.executeUpdate();
					}
					rs.next();
				}
			}

			stm = con.prepareStatement("DELETE FROM railroad.t_w WHERE t_id=?");
			stm.setInt(1, number);
			stm.executeUpdate();

			for (int i = 0; i < 10; i++) {
				stm = con
						.prepareStatement("DELETE FROM railroad.wagon WHERE idwagon=?");
				stm.setInt(1, masWagonId[i]);
				stm.executeUpdate();
			}

			stm = con
					.prepareStatement("DELETE FROM railroad.train WHERE idtrain=?");
			stm.setInt(1, number);
			stm.executeUpdate();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public boolean addStation(String name) {
		try {
			stm = con
					.prepareStatement("INSERT INTO railroad.stations (name) VALUES (?);");// add
																							// station
			stm.setString(1, name);
			stm.executeUpdate();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public boolean deleteStation(String stName) {
		try {
			int stationId;
			int routeId;
			stm = con
					.prepareStatement("SELECT idstations FROM railroad.stations WHERE name = ?");

			stm.setString(1, stName);
			ResultSet rs = stm.executeQuery();
			rs.next();
			stationId = rs.getInt(1);

			stm = con
					.prepareStatement("SELECT idroutes, first_station, last_station FROM railroad.routes");
			rs = stm.executeQuery();
			while (rs.next()) {
				if (rs.getInt(2) == stationId) {// first station
					routeId = rs.getInt(1);
					stm = con
							.prepareStatement("SELECT s_id FROM railroad.r_s WHERE r_id=? AND station_number =?;");
					stm.setInt(1, routeId);
					stm.setInt(2, 2);
					ResultSet rs2 = stm.executeQuery();
					if (rs2.next()) {// **********************************************************
						stationId = rs2.getInt(1);
						stm = con
								.prepareStatement("DELETE FROM railroad.r_s WHERE r_id=? AND s_id =? AND station_number = ?;");
						stm.setInt(1, routeId);
						stm.setInt(2, stationId);
						stm.setInt(3, 2);
						stm.executeUpdate();

						// ****
						stm = con
								.prepareStatement("SELECT s_id, station_number FROM railroad.r_s WHERE r_id=?");
						stm.setInt(1, routeId);
						rs2 = stm.executeQuery();
						while (rs2.next()) {
							stm = con
									.prepareStatement("UPDATE railroad.r_s SET station_number = ? WHERE r_id = ? AND s_id=?");
							stm.setInt(1, rs2.getInt(2) - 1);
							stm.setInt(2, routeId);
							stm.setInt(3, rs2.getInt(1));
							stm.executeUpdate();
						}

						// *****
						stm = con
								.prepareStatement("UPDATE railroad.routes SET first_station = ? WHERE idroutes = ?");
						stm.setInt(1, stationId);
						stm.setInt(2, routeId);
						stm.executeUpdate();
					} else {

						// ЕСЛИ БЫЛО ВСЕГО ДВЕ СТАНЦИИ И Я УДАЛИЛ ПЕРВУЮ, ТО
						// НАДО
						// УДАЛИТЬ ВЕСЬ МАРШРУТ И УПОМИНАНИЯ О НЕМ
						stm = con
								.prepareStatement("SELECT COUNT(t_id) FROM railroad.r_t WHERE r_id = ?;");
						stm.setInt(1, routeId);
						rs2 = stm.executeQuery();
						if (rs2.next()) {
							int[] masTrainsId = new int[rs2.getInt(1)];
							stm = con
									.prepareStatement("SELECT t_id FROM railroad.r_t WHERE r_id = ?;");
							stm.setInt(1, routeId);
							ResultSet rs3 = stm.executeQuery();
							rs3.next();
							for (int i = 0; i < masTrainsId.length; i++) {
								masTrainsId[i] = rs3.getInt(1);
								rs3.next();
							}
							stm = con
									.prepareStatement("DELETE FROM railroad.r_t WHERE r_id=?");
							stm.setInt(1, routeId);
							stm.executeUpdate();
							for (int i = 0; i < masTrainsId.length; i++) {
								deleteTrain(masTrainsId[i]);
							}

							stm = con
									.prepareStatement("DELETE FROM railroad.routes WHERE idroutes=?");
							stm.setInt(1, routeId);
							stm.executeUpdate();
						} else {
							stm = con
									.prepareStatement("DELETE FROM railroad.routes WHERE idroutes=?");
							stm.setInt(1, routeId);
							stm.executeUpdate();
						}
					}
					stm = con
							.prepareStatement("DELETE FROM railroad.stations WHERE name=?");
					stm.setString(1, stName);
					stm.executeUpdate();

				}
				if (rs.getInt(3) == stationId) {// last station
					routeId = rs.getInt(1);
				}
			}
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public List<RoadInfo> getRoadInfoByStations(String station1, String station2) {
		try {
			List<RoadInfo> info = new ArrayList<RoadInfo>();
			boolean isFirstStation = false;
			boolean isLastStation = false;
			int idStation1;
			int idStation2;
			int ordinalNumberStation1 = 0;
			int ordinalNumberStation2 = 0;
			Date departmentTime = null;
			Date arrivalTime = null;
			Date departmentDate = null;
			Date arrivalDate = null;
			long inRoad = 0;
			int route = 0;
			int trainNumber = 0;
			int pricePlackart = 0;
			int priceKupe = 0;
			int priceCommon = 0;
			int freePlacesPlackart = 0;
			int freePlacesKupe = 0;
			int freePlacesCommon = 0;
			int wagonType;
			// получаю Айдишники станций по названию.
			stm = con
					.prepareStatement("SELECT idstations FROM railroad.stations WHERE name = ?");
			stm.setString(1, station1);
			ResultSet rs = stm.executeQuery();
			rs.next();
			idStation1 = rs.getInt(1);// Айдишник первой станции
			stm = con
					.prepareStatement("SELECT idstations FROM railroad.stations WHERE name = ?");
			stm.setString(1, station2);
			rs = stm.executeQuery();
			rs.next();
			idStation2 = rs.getInt(1);// Айдишник последней станции
			// ********************************
			// Ищу маршрут по айдишнику станции

			stm = con
					.prepareStatement("SELECT idroutes, departure_time FROM railroad.routes WHERE first_station = ?");
			stm.setInt(1, idStation1);
			ResultSet rsSearchAmongFirstStations = stm.executeQuery();// просматриваем
																		// все
																		// первые
																		// станции
																		// в
																		// поиске
																		// нужной
			stm = con
					.prepareStatement("SELECT r_id, station_number, department_time FROM railroad.r_s WHERE s_id = ?");
			stm.setInt(1, idStation1);
			ResultSet rsSearchFirstAmongIntermediateStations = stm
					.executeQuery();// просматриваем промежуточные станции в
									// поиске нужной

			boolean tempFirstStation;
			boolean tempIntermediateFirstStation;

			tempFirstStation = rsSearchAmongFirstStations.next();
			tempIntermediateFirstStation = rsSearchFirstAmongIntermediateStations
					.next();

			while (tempFirstStation != false
					|| tempIntermediateFirstStation != false) {// цикл
																// прохождения
																// всех
																// найденных
																// первых
																// станций и
																// промежуточных

				if (!tempFirstStation && tempIntermediateFirstStation) {
					route = rsSearchFirstAmongIntermediateStations.getInt(1);
					ordinalNumberStation1 = rsSearchFirstAmongIntermediateStations
							.getInt(2);
					departmentDate = rsSearchFirstAmongIntermediateStations
							.getDate(3);
					departmentTime = rsSearchFirstAmongIntermediateStations
							.getTime(3);
					tempIntermediateFirstStation = rsSearchFirstAmongIntermediateStations
							.next();
				}
				if (tempFirstStation) {
					route = rsSearchAmongFirstStations.getInt(1);
					departmentDate = rsSearchAmongFirstStations.getDate(2);
					departmentTime = rsSearchAmongFirstStations.getTime(2);
					isFirstStation = true;
					tempFirstStation = rsSearchAmongFirstStations.next();
				}

				stm = con
						.prepareStatement("SELECT arrival_time FROM railroad.routes WHERE last_station = ? AND idroutes = ?");
				stm.setInt(1, idStation2);
				stm.setInt(2, route);
				ResultSet rsSearchAmongLastStations = stm.executeQuery();// просматриваем
																			// все
																			// последние
																			// станции
																			// в
																			// поиске
																			// нужной
				stm = con
						.prepareStatement("SELECT station_number, arrival_time FROM railroad.r_s WHERE s_id = ? AND r_id = ?");
				stm.setInt(1, idStation2);
				stm.setInt(2, route);
				ResultSet rsSearchLastAmongIntermediateStations = stm
						.executeQuery();// просматриваем промежуточные станции в
										// поиске нужной

				boolean tempLastStation;
				boolean tempIntermediateLastStation;

				tempLastStation = rsSearchAmongLastStations.next();
				tempIntermediateLastStation = rsSearchLastAmongIntermediateStations
						.next();

				while (tempLastStation != false
						|| tempIntermediateLastStation != false) { // цикл
																	// прохождения
																	// всех
																	// найденных
																	// последних
																	// станций и
																	// промежуточных
																	// на
																	// заданном
																	// марщруте
					if (!tempLastStation && tempIntermediateLastStation) {// если
																			// подходящих
																			// последних
																			// станций
																			// нет,
																			// просматриваем
																			// промежуточные
						ordinalNumberStation2 = rsSearchLastAmongIntermediateStations
								.getInt(1);
						arrivalDate = rsSearchLastAmongIntermediateStations
								.getDate(2);
						arrivalTime = rsSearchLastAmongIntermediateStations
								.getTime(2);
						tempIntermediateLastStation = rsSearchLastAmongIntermediateStations
								.next();
					}
					if (tempLastStation) {// если есть последние станции
						arrivalDate = rsSearchAmongLastStations.getDate(1);
						arrivalTime = rsSearchAmongLastStations.getTime(1);
						isLastStation = true;
						tempLastStation = rsSearchAmongLastStations.next();

					}

					if ((ordinalNumberStation2 > ordinalNumberStation1)
							|| isLastStation || isFirstStation) {// Надо
																	// проверить,
																	// стоит ли
																	// эта
																	// станция
																	// ПОСЛЕ
																	// первой
																	// заданной

						stm = con
								.prepareStatement("SELECT t_id FROM railroad.r_t WHERE r_id = ?");
						stm.setInt(1, route);
						ResultSet rsSearchTrains = stm.executeQuery();// просматриваем
																		// все
																		// поезда
																		// на
																		// данном
																		// маршруте
						while (rsSearchTrains.next()) {// пока есть поезда

							trainNumber = rsSearchTrains.getInt(1);
							stm = con
									.prepareStatement("SELECT w_id FROM railroad.t_w WHERE t_id = ?");
							stm.setInt(1, trainNumber);
							ResultSet rsSearchWagons = stm.executeQuery();// просматриваем
																			// вагоны
																			// принадлежащие
																			// поезду
							while (rsSearchWagons.next()) {// пока есть вагоны

								stm = con
										.prepareStatement("SELECT type FROM railroad.wagon WHERE idwagon = ?");
								stm.setInt(1, rsSearchWagons.getInt(1));
								ResultSet rsSearchWagonType = stm
										.executeQuery();// просматриваем айди
														// типа вагона
								rsSearchWagonType.next();
								wagonType = rsSearchWagonType.getInt(1);

								stm = con
										.prepareStatement("SELECT p_id FROM railroad.w_p WHERE w_id = ?");
								stm.setInt(1, rsSearchWagons.getInt(1));
								ResultSet rsSearchPlaces = stm.executeQuery();// просматриваем
																				// места
																				// в
																				// вагоне
								while (rsSearchPlaces.next()) {// пока есть
																// места

									int i = 0; // с этой станции начать
									int j = 0; // на этой закончить
									if (isFirstStation) {// если первая заданная
															// станция -Первая,
															// то просматриваем
															// с начала
										i = 1;
									}
									if (!isFirstStation) {// если не Первая, то
															// с её номера в
															// маршруте
										i = ordinalNumberStation1;
									}
									if (isLastStation) {// если вторая заданная
														// станция -Последняя,
														// то просматриваем пока
														// не выдаст false
														// ResulSet
										j = Integer.MAX_VALUE;
									}
									if (!isLastStation) {// если не Последняя,
															// то с её номера в
															// маршруте
										j = ordinalNumberStation2 - 1;
									}
									int tempPrice = 0;
									boolean tempFreePlace = true;
									for (int temp = i; temp <= j; temp++) { // просматриваем
																			// состояние
																			// мест
																			// от
																			// каждой
																			// станции
																			// в
																			// нужном
																			// маршруте
										stm = con
												.prepareStatement("SELECT status, price FROM railroad.p_st WHERE p_id = ? AND route_id = ? AND station_number = ?");
										stm.setInt(1, rsSearchPlaces.getInt(1));
										stm.setInt(2, route);
										stm.setInt(3, temp);
										ResultSet rsSearchPlacesStatus = stm
												.executeQuery();// смотрим цену
																// места и
																// статус
										if (!rsSearchPlacesStatus.next()) {// если
																			// все
																			// станции
																			// кончились
																			// (тоесть
																			// вторая
																			// станция
																			// была
																			// Послденей)
											break;
										}
										if (rsSearchPlacesStatus.getInt(1) == 2) {// если
																					// хоть
																					// на
																					// одной
																					// странции
																					// место
																					// занято,
																					// то
																					// оно
																					// занято
																					// на
																					// всем
																					// маршруте
											tempFreePlace = false;
										}
										tempPrice += rsSearchPlacesStatus
												.getInt(2);// складываем цены за
															// каждую станцию.

									}
									if (tempFreePlace) {// если место свободно
														// на протяжении вего
														// пути - оно считается
														// свободным.
										if (wagonType == 1) {// в зависимости от
																// типа вагона
																// увеличиваем
																// кол-во
																// свободных
																// мест.
											freePlacesPlackart++;
											pricePlackart = tempPrice;
										}
										if (wagonType == 2) {
											freePlacesKupe++;
											priceKupe = tempPrice;
										}
										if (wagonType == 3) {
											freePlacesCommon++;
											priceCommon = tempPrice;
										}
									}

								}

							}

						}
					}
					inRoad = (arrivalDate.getTime() + arrivalTime.getTime() + 7200000)
							- (departmentDate.getTime()
									+ departmentTime.getTime() + 7200000);
					// здесь надо добавить объект с информацией.
					info.add(new RoadInfo(trainNumber, departmentDate,
							departmentTime, station1, arrivalDate, arrivalTime,
							station2, inRoad, freePlacesPlackart,
							pricePlackart, freePlacesKupe, priceKupe,
							freePlacesCommon, priceCommon, "some link"));

				}

			}
			return info;

			// **************************
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	/*
	 * public boolean forPlacesSt() { try { stm = con .prepareStatement(
	 * "INSERT INTO railroad.p_st (p_id, station_number, status, price, route_id) VALUES (?,?,?,?,?);"
	 * ); for(int i = 1; i<43;i++ ){ for(int j=1;j<5;j++){ stm.setInt(1, i);
	 * stm.setInt(2, j); stm.setInt(3, 1); stm.setInt(4, 100); stm.setInt(5, 1);
	 * stm.executeUpdate(); }
	 * 
	 * } return true;
	 * 
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * return false; }
	 */
	public List<String> listStations() {
		try {
			List<String> stations = new ArrayList<String>();
			stm = con.prepareStatement("SELECT name FROM railroad.stations;");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				stations.add(rs.getString(1));
			}

			return stations;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {
		DAORailroad dao = DAORailroad.getDAORailroad();
		// dao.addTrain(121);
		// dao.deleteTrain(121);
		// dao.deleteStation("Pushkinskaya");
		// System.out.println(dao.getUser("qwerty"));
		// List<RoadInfo> info = dao.getRoadInfoByStations("Heroiv Praci",
		// "Universitet");
		List<RoadInfo> info = dao.getRoadInfoByStations("Academica Pavlova",
				"Heroiv Praci");
		for (RoadInfo a : info) {
			System.out.println(a.toString());
		}
		
	
	}

}
