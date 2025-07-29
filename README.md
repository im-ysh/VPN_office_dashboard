
---

# 🛡️ OpenVPN Office Dashboard

This project is a complete VPN Office Management Dashboard built using Java Servlets (Backend), MySQL Database, and HTML/CSS/JS (Frontend). It provides secure access and monitoring of VPN users within an office setup using Tailscale VPN.

---

## ✨ What This Project Does

This system allows organizations to:

* ✅ User authentication (Login & Registration)
* ✅ Device log submission
* ✅ User log fetching
* ✅ Training log tracking
* ✅ System audit log tracking
* ✅ Role-based modules: Admin, Marketing, Training

It simulates a secure private network (like a WAN) over the internet using encrypted tunnels and virtual connections via Tailscale.

---

## 💡 Why This Project is Useful

* ✅ Secure Office VPN Setup using Tailscale
* ✅ Provides secure, remote access to a private dashboard from anywhere via Tailscale
* ✅ Admins can monitor device logs, user activity, training scores, and audit actions
* ✅ Remote employees can connect, authenticate, and use the internal system without exposing it publicly
* ✅ Suitable for office setups, project teams, or remote work environments

---

## 🚀 How to Get Started

### 1. Clone the Repository

```bash
git clone https://github.com/Meghana-Yarlagadda/openvpn-dashboard.git
cd openvpn-dashboard
```

### 2. Set Up the Database

Import the schema from `database/schema.sql` into your MySQL server:

```bash
mysql -u root -p < database/schema.sql
```

### 3. Start Backend Server

* Open `backend/` folder in Eclipse or IntelliJ as a Dynamic Web Project
* Configure your MySQL credentials inside `DBUtil.java`
* Deploy the backend on Apache Tomcat 9

### 4. Connect via VPN (Tailscale)

* Install Tailscale on all devices (Admin & Employees)
* Join the same Tailscale network
* Use Admin’s Tailscale IP address in your JDBC URL or frontend fetch requests

---

## 🌐 Example Flow (User Journey)

Example user flow for colleague “Maggi”:

1. Maggi installs Tailscale and connects to the Admin's shared VPN.
2. Maggi accesses the frontend via Admin’s Tailscale IP.
3. Registers (or is registered by Admin), logs in with credentials.
4. Maggi logs device VPN usage via the Device Logs form.
5. Completes training modules.
6. Admin views all logs and audit trails through dashboard.

---

## 💻 Backend API Endpoints

| Endpoint                           | Method | Purpose                         |
| ---------------------------------- | ------ | ------------------------------- |
| `/vpn-backend/login`               | POST   | User login                      |
| `/vpn-backend/register`            | POST   | User registration               |
| `/vpn-backend/logs`                | POST   | Submit device logs              |
| `/vpn-backend/fetch-user-logs`     | POST   | Fetch specific user device logs |
| `/vpn-backend/training`            | POST   | Submit training logs            |
| `/vpn-backend/fetch-training-logs` | POST   | Fetch training logs             |
| `/vpn-backend/fetch-audit-logs`    | GET    | Fetch system audit logs         |

---

## 🛠️ Technologies Used

* Java Servlet (J2EE)
* Apache Tomcat 9
* MySQL 8.0+
* Tailscale VPN for secure network connectivity
* HTML, CSS, JavaScript

---
