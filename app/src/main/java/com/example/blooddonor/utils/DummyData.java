package com.example.blooddonor.utils;

import com.example.blooddonor.model.Badge;
import com.example.blooddonor.model.BloodRequest;
import com.example.blooddonor.model.DonationHistory;
import com.example.blooddonor.model.Hospital;
import com.example.blooddonor.model.User;

import java.util.ArrayList;
import java.util.List;

public class DummyData {

    public static List<User> getSampleDonors() {
        List<User> donors = new ArrayList<>();

        donors.add(new User("USR001", "Rahul Sharma", "rahul@gmail.com", "9876543210",
                "pass", "A+", "Donor", 23.0225, 72.5714, "10 Jan 2024", true, 5, 500));
        donors.add(new User("USR002", "Priya Patel", "priya@gmail.com", "9876543211",
                "pass", "O+", "Donor", 23.0300, 72.5800, "05 Feb 2024", true, 3, 300));
        donors.add(new User("USR003", "Amit Kumar", "amit@gmail.com", "9876543212",
                "pass", "B+", "Donor", 23.0150, 72.5650, "20 Dec 2023", false, 7, 700));
        donors.add(new User("USR004", "Sneha Desai", "sneha@gmail.com", "9876543213",
                "pass", "AB+", "Donor", 23.0400, 72.5900, "15 Mar 2024", true, 2, 200));
        donors.add(new User("USR005", "Vikram Singh", "vikram@gmail.com", "9876543214",
                "pass", "O-", "Donor", 23.0100, 72.5600, "01 Jan 2024", true, 10, 1000));
        donors.add(new User("USR006", "Kavya Mehta", "kavya@gmail.com", "9876543215",
                "pass", "A-", "Donor", 23.0500, 72.6000, "25 Nov 2023", false, 4, 400));
        donors.add(new User("USR007", "Ravi Joshi", "ravi@gmail.com", "9876543216",
                "pass", "B-", "Donor", 23.0350, 72.5750, "10 Mar 2024", true, 1, 100));
        donors.add(new User("USR008", "Ananya Shah", "ananya@gmail.com", "9876543217",
                "pass", "AB-", "Donor", 23.0250, 72.5700, "20 Feb 2024", true, 6, 600));
        donors.add(new User("USR009", "Deepak Modi", "deepak@gmail.com", "9876543218",
                "pass", "O+", "Donor", 23.0180, 72.5680, "05 Jan 2024", true, 8, 800));
        donors.add(new User("USR010", "Meena Gupta", "meena@gmail.com", "9876543219",
                "pass", "A+", "Donor", 23.0290, 72.5770, "28 Feb 2024", false, 12, 1200));

        return donors;
    }

    public static List<BloodRequest> getSampleRequests() {
        List<BloodRequest> requests = new ArrayList<>();

        requests.add(new BloodRequest("REQ001", "Suresh Patel", "9123456780",
                "O+", "Civil Hospital, Ahmedabad", "06 Apr 2024, 10:00 AM",
                "Critical", 23.0225, 72.5714, "05 Apr 2024"));
        requests.add(new BloodRequest("REQ002", "Nita Shah", "9123456781",
                "A+", "Sterling Hospital, Ahmedabad", "07 Apr 2024, 2:00 PM",
                "Urgent", 23.0300, 72.5800, "05 Apr 2024"));
        requests.add(new BloodRequest("REQ003", "Ramesh Kumar", "9123456782",
                "B+", "SAL Hospital, Ahmedabad", "08 Apr 2024, 9:00 AM",
                "Normal", 23.0150, 72.5650, "04 Apr 2024"));
        requests.add(new BloodRequest("REQ004", "Geeta Patel", "9123456783",
                "AB-", "Apollo Hospital, Ahmedabad", "06 Apr 2024, 5:00 PM",
                "Critical", 23.0400, 72.5900, "05 Apr 2024"));
        requests.add(new BloodRequest("REQ005", "Dinesh Verma", "9123456784",
                "O-", "CIMS Hospital, Ahmedabad", "09 Apr 2024, 11:00 AM",
                "Urgent", 23.0100, 72.5600, "06 Apr 2024"));

        return requests;
    }

    public static List<Hospital> getSampleHospitals() {
        List<Hospital> hospitals = new ArrayList<>();

        hospitals.add(new Hospital("Civil Hospital Ahmedabad",
                "Asarwa, Ahmedabad – 380016", "07922683721", "A+, O+, B+, AB+ available", true));
        hospitals.add(new Hospital("Sterling Hospital",
                "Gurukul Road, Memnagar, Ahmedabad – 380052", "07926764021", "A+, A-, O+ available", true));
        hospitals.add(new Hospital("SAL Hospital",
                "Drive-In Road, Ahmedabad – 380054", "07966309000", "All groups available", true));
        hospitals.add(new Hospital("Apollo Hospital",
                "Plot No. 1A, GIDC, Bhat, Ahmedabad – 382428", "07968309090", "O-, AB- limited", true));
        hospitals.add(new Hospital("CIMS Hospital",
                "Nr. Shukan Mall, Off Science City, Ahmedabad – 380060", "07930010000", "B+, O+ available", true));
        hospitals.add(new Hospital("Red Cross Blood Bank",
                "Paldi, Ahmedabad – 380007", "07926584261", "All groups – large stock", true));

        return hospitals;
    }

    public static List<DonationHistory> getSampleHistory() {
        List<DonationHistory> history = new ArrayList<>();

        history.add(new DonationHistory("10 Jan 2024", "Civil Hospital, Ahmedabad", "1 Unit (450ml)", "A+"));
        history.add(new DonationHistory("15 Sep 2023", "Sterling Hospital, Ahmedabad", "1 Unit (450ml)", "A+"));
        history.add(new DonationHistory("20 May 2023", "SAL Hospital, Ahmedabad", "1 Unit (450ml)", "A+"));

        return history;
    }

    public static List<Badge> getAllBadges(int totalDonations) {
        List<Badge> badges = new ArrayList<>();

        badges.add(new Badge("First Drop", "Complete your first donation",
                "🩸", Constants.BADGE_FIRST_DROP, totalDonations >= Constants.BADGE_FIRST_DROP));
        badges.add(new Badge("Life Saver", "Donate blood 3 times",
                "💉", Constants.BADGE_LIFE_SAVER, totalDonations >= Constants.BADGE_LIFE_SAVER));
        badges.add(new Badge("Hero Donor", "Donate blood 5 times",
                "🦸", Constants.BADGE_HERO_DONOR, totalDonations >= Constants.BADGE_HERO_DONOR));
        badges.add(new Badge("Champion", "Reach 10 donations",
                "🏆", Constants.BADGE_CHAMPION, totalDonations >= Constants.BADGE_CHAMPION));
        badges.add(new Badge("Legend", "Complete 25 donations",
                "⭐", Constants.BADGE_LEGEND, totalDonations >= Constants.BADGE_LEGEND));

        return badges;
    }
}
