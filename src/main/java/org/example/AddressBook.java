package org.example;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BuddyInfo> buddies;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public List<BuddyInfo> getBuddies() { return buddies; }
    public void setBuddies(List<BuddyInfo> buddies) { this.buddies = buddies; }
    // ...existing constructor/methods unchanged
    // Buddies collection


    public AddressBook() {
        this.buddies = new ArrayList<>();
    }

    // Add a buddy
    public void addBuddy(BuddyInfo buddy) {
        if (buddy != null) {
            buddies.add(buddy);
        }
    }

    // Remove a buddy by BuddyInfo
    public boolean removeBuddy(BuddyInfo buddy) {
        return buddies.remove(buddy);
    }
    // Remove a buddy by index
    public BuddyInfo removeBuddy(int index) {
        if (index >= 0 && index < buddies.size()) {
            return buddies.remove(index);
        }
        return null;
    }

    // Get the total count of buddies
    public int size() {
        return buddies.size();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (BuddyInfo b : buddies) {
            sb.append(b).append("\n");
        }
        return sb.toString();
    }


}

