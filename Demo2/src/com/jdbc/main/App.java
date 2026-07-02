package com.jdbc.main;

import com.jdbc.dto.OwnerDTO;
import com.jdbc.service.OwnerService;
import com.jdbc.service.impl.OwnerServiceImpl;
import com.jdbc.util.InputUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		new App().run();
	}

	public void run() {

		OwnerService ownerService = new OwnerServiceImpl();

		System.out.println("Welcome to Petistaan");

		try (Scanner sc = new Scanner(System.in)) {

			do {

				int choice = InputUtil.acceptMenuOption(sc);

				switch (choice) {

					case 1:
						System.out.println("Enter Owner Details");
						OwnerDTO ownerDTO = InputUtil.acceptOwnerDetailsToSave(sc);
						ownerService.saveOwner(ownerDTO);
						System.out.println("Owner details saved successfully");
						break;

					case 2:
						int ownerId = InputUtil.acceptOwnerIdToOperate(sc);
						OwnerDTO owner = ownerService.findOwner(ownerId);
						System.out.println(owner);
						break;

					case 3:
						ownerId = InputUtil.acceptOwnerIdToOperate(sc);
						String petName = InputUtil.acceptPetDetailsToUpdate(sc);
						ownerService.updatePetDetails(ownerId, petName);
						System.out.println("Pet details updated successfully");
						break;

					case 4:
						ownerId = InputUtil.acceptOwnerIdToOperate(sc);
						ownerService.deleteOwner(ownerId);
						System.out.println("Owner deleted successfully");
						break;

					case 5:
						List<OwnerDTO> ownerList = ownerService.findAllOwners();

						if (ownerList.isEmpty()) {
							System.out.println("No Owners Found.");
						} else {
							ownerList.forEach(System.out::println);
						}
						break;

					case 6:
						String ownerEmailId = InputUtil.acceptOwnerEmailIdToOperate(sc);
						LocalDate petBirthDate = InputUtil.acceptPetBirthDateToOperate(sc);

						ownerList = ownerService.findOwner(ownerEmailId, petBirthDate);

						if (ownerList.isEmpty()) {
							System.out.println("No Owners Found.");
						} else {
							ownerList.forEach(System.out::println);
						}
						break;

					default:
						System.out.println("Invalid Choice");
				}

			} while (InputUtil.wantToContinue(sc));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}