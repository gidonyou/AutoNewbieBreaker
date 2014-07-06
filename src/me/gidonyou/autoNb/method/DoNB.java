package me.gidonyou.autoNb.method;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import me.gidonyou.autoNb.AutoNB;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DoNB {
	private AutoNB plugin;

	public DoNB(final AutoNB instance) {
		this.plugin = instance;
	}

	@SuppressWarnings("unused")
	public String[] getRank(final String child) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		// TODO IMPORT CONFIG --
		plugin.reloadConfig();
		String username = plugin.getConfig().getString("username");
		String pass = plugin.getConfig().getString("password");

		final String[] PEXinfo = { "1", "1" };
		final String url = "jdbc:mysql://localhost/PEX?user=" + username
				+ "&password=" + pass;
		final String query = "SELECT * FROM `permissions_inheritance` WHERE `child` LIKE '"
				+ child + "' LIMIT 0 , 1";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				PEXinfo[0] = rs.getString(3);
				PEXinfo[1] = rs.getString(1);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e3) {
					e3.printStackTrace();
				}
			}
			rs = null;
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e3) {
					e3.printStackTrace();
				}
			}
			stmt = null;
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e3) {
					e3.printStackTrace();
				}
			}
			conn = null;
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
		}
		rs = null;
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
		}
		stmt = null;
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
		}
		conn = null;
		return PEXinfo;
	}

	public String getHTML(final String urlToRead) {
		String result = "";
		try {
			final URL url = new URL(urlToRead);
			final HttpURLConnection conn = (HttpURLConnection) url
					.openConnection();
			conn.setRequestMethod("GET");
			final BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				result = String.valueOf(result) + line;
			}
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean portIsOpen(final String ip, final int port, final int timeout) {
		try {
			final Socket socket = new Socket();
			socket.connect(new InetSocketAddress(ip, port), timeout);
			socket.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean nb() {
		if (this.portIsOpen("mc.snsh.me", 80, 1000)) {
			this.getHTML("http://mc.snsh.me/pex/run.php");
		}
		Integer nbCount = 0;
		Player[] onlinePlayers;
		for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
			final Player p = onlinePlayers[i];
			try {
				p.getName();
				if (!p.hasPermission("newbieban.notnewbie")) {
					String[] PEXinfo = new String[2];
					PEXinfo = this.getRank(p.getName());
					if (PEXinfo[0].equalsIgnoreCase("3")) {
						Bukkit.dispatchCommand(
								(CommandSender) Bukkit.getConsoleSender(),
								"setrank " + p.getName() + " 3");
						Bukkit.dispatchCommand(
								(CommandSender) Bukkit.getConsoleSender(),
								"m "
										+ p.getName()
										+ " [User] \ub4f1\uae09\uc73c\ub85c \ub4f1\uc5c5\ub418\uc168\uc2b5\ub2c8\ub2e4 ^^");
					} else {
						Bukkit.dispatchCommand(
								(CommandSender) Bukkit.getConsoleSender(),
								"tempban "
										+ p.getName()
										+ " 5m "
										+ p.getName()
										+ "\ub2d8, \uc218\uc138\ubbf8\uc11c\ubc84\uc5d0 \uc624\uc2e0\uac83\uc744 \ud658\uc601\ud569\ub2c8\ub2e4! \uac00\uc785\uc778\uc0ac \uc368\uc8fc\uc2dc\uace0 5\ubd84 \ud6c4 \ub4e4\uc5b4\uc640\uc8fc\uc138\uc694~ ^^ \uc8fc\uc18c: http://mc.snsh.me/");
						++nbCount;
					}
					PEXinfo = null;
				}
			} catch (Exception e) {
				System.out.println("[NewbieBan] " + e.getMessage());
			}
		}
		if (nbCount > 0) {
			if (nbCount == 1) {
				Bukkit.broadcastMessage(ChatColor.GRAY
						+ "[\ub274\ube44\ube0c\ub808\uc774\ucee4]"
						+ ChatColor.AQUA
						+ nbCount.toString()
						+ ChatColor.YELLOW
						+ "\uba85\uc758 \ub274\ube44\uac00 \ud648\ud398\uc774\uc9c0\uc5d0 \uac00\uc785\uc778\uc0ac\ub97c \ud558\ub7ec \ub098\uac14\uc2b5\ub2c8\ub2e4.");
			} else {
				Bukkit.broadcastMessage(ChatColor.GRAY
						+ "[\ub274\ube44\ube0c\ub808\uc774\ucee4]"
						+ ChatColor.AQUA
						+ nbCount.toString()
						+ ChatColor.YELLOW
						+ "\uba85\uc758 \ub274\ube44\ub4e4\uc774 \ud648\ud398\uc774\uc9c0\uc5d0 \uac00\uc785\uc778\uc0ac\ub97c \ud558\ub7ec \ub098\uac14\uc2b5\ub2c8\ub2e4.");
			}
		} else {
			Bukkit.broadcastMessage(ChatColor.GRAY
					+ "[\ub274\ube44\ube0c\ub808\uc774\ucee4]"
					+ ChatColor.YELLOW
					+ "\ub274\ube44\uac00.. \uc5c6\uc5c4..\u3160\u3160");
		}
		return true;
	}
}
