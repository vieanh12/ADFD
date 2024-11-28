package com.examt2303m.controller;

import com.examt2303m.dto.ViewListPlayer;
import com.examt2303m.entity.Indexer;
import com.examt2303m.entity.Player;
import com.examt2303m.entity.PlayerIndex;
import com.examt2303m.service.IndexerService;
import com.examt2303m.service.PlayerIndexService;
import com.examt2303m.service.PlayerService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/player")
public class PlayerController extends HttpServlet {

    private PlayerService playerService;
    private IndexerService indexerService;
    private PlayerIndexService playerIndexService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.playerService = new PlayerService();
        this.indexerService = new IndexerService();
        this.playerIndexService = new PlayerIndexService();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            List<Player> listPlayer = playerService.getAll();
            List<Indexer> listIndex = indexerService.getAll();
            List<ViewListPlayer> viewListPlayerList = new ArrayList<>();
            for (Player player : listPlayer) {
                ViewListPlayer viewListPlayer = new ViewListPlayer();
                viewListPlayer.setId(player.getPlayerId());
                viewListPlayer.setName(player.getName());
                viewListPlayer.setAge(player.getAge());
                viewListPlayer.setIndexName(player.getIndexer().getName());
                PlayerIndex playerIndex = playerIndexService.getPlayerIndexByPlayerAndIndexer(player, player.getIndexer());
                viewListPlayer.setValue(playerIndex.getValue());
                viewListPlayerList.add(viewListPlayer);
            }

            req.setAttribute("viewListPlayerList", viewListPlayerList);
            req.setAttribute("indexer", listIndex);
            RequestDispatcher dispatcher = req.getRequestDispatcher("player.jsp");
            dispatcher.forward(req, resp);
        }catch (Exception e) {

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {

            String action = req.getParameter("action");
            if ("delete".equals(action)) {
                int playerId = Integer.parseInt(req.getParameter("playerId"));
                Player player = playerService.getPlayerById(playerId);
                int indexerId = player.getIndexer().getIndexId();



                res.sendRedirect(req.getContextPath() + "/player");
                return;
            }

            // Lấy dữ liệu từ form
            String playerName = req.getParameter("name");
            String playerAge = String.valueOf(req.getParameter("age"));
            int indexId = Integer.parseInt(req.getParameter("indexId"));
            float value = Float.parseFloat(req.getParameter("value"));

            // Tạo đối tượng Player
            Indexer indexer = indexerService.getIndexerById(indexId);

            Player player = new Player();
            player.setName(playerName);
            player.setFullName(playerName);
            player.setAge(playerAge);
            player.setIndexer(indexer);

            playerService.savePlayer(player);

            PlayerIndex playerIndex = new PlayerIndex();
            playerIndex.setPlayer(player);
            playerIndex.setIndexer(indexer);
            playerIndex.setValue(value);
            playerIndexService.savePlayerIndex(playerIndex);
            res.sendRedirect(req.getContextPath() + "/player");
        } catch (Exception e) {
            e.printStackTrace();
            res.getWriter().write("Error: " + e.getMessage());
        }
    }
}
