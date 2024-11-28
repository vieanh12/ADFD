<%@ page import="java.util.List" %>
<%@ page import="com.examt2303m.dypham.entity.Player" %>
<%@ page import="com.examt2303m.dypham.entity.Indexer" %>
<%@ page import="com.examt2303m.dypham.dto.ViewListPlayer" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Listing</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <h1>List of Player</h1>
    <button class="btn btn-primary my-3" data-bs-toggle="modal" data-bs-target="#addPlayer">Add</button>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Player name</th>
            <th scope="col">Player age</th>
            <th scope="col">Index name</th>
            <th scope="col">Value</th>
            <th scope="col">Actions</th>
        </tr>
        </thead>
        <tbody>
        <% for(ViewListPlayer player : (List<ViewListPlayer>)request.getAttribute("viewListPlayerList")){ %>
        <tr>
            <th scope="row"><%= player.getId() %></th>
            <td><%= player.getName() %></td>
            <td><%= player.getAge() %></td>
            <td><%= player.getIndexName() %></td>
            <td><%= player.getValue() %></td>
            <td>
                <button class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#editStudentModal<%= player.getId() %>">Edit</button>

                <form action="${pageContext.request.contextPath}/student" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="playerId" value="<%= player.getId() %>">
                    <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this student?');">Delete</button>
                </form>
            </td>
        </tr>

        <% } %>
        </tbody>


    </table>

    <!-- Modal Add Player -->
    <div class="modal fade" id="addPlayer" tabindex="-1" aria-labelledby="addPlayerLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="${pageContext.request.contextPath}/player" method="post">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addPlayerLabel">Add Player</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="playerName" class="form-label">Player Name</label>
                            <input type="text" class="form-control" id="playerName" name="name" required>
                        </div>
                        <div class="mb-3">
                            <label for="playerAge" class="form-label">Player Age</label>
                            <input type="number" class="form-control" id="playerAge" name="age" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Index name</label>
                            <select name="indexId" class="form-control" id="indexName" onchange="fetchIndexValue(this.value)">
                                <%
                                    List<Indexer> indexer = (List<Indexer>) request.getAttribute("indexer");
                                    for (Indexer i : indexer) {
                                %>
                                <option value="<%= i.getIndexId() %>"><%= i.getName() %></option>
                                <%
                                    }
                                %>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="value" class="form-label">Value</label>
                            <input type="number" class="form-control" id="value" name="value" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save</button>
                    </div>
                </form>
            </div>
        </div>
    </div>






</div>
</body>
</html>
