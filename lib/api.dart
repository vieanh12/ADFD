import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:exam/model/place.dart';

class ApiService {
  static const String apiUrl = 'http://localhost:8080/api/places';

  static Future<List<Place>> getAllPlaces() async {
    final response = await http.get(Uri.parse(apiUrl));

    if (response.statusCode == 200) {
      List<dynamic> data = jsonDecode(response.body);
      return data.map((item) => Place.fromJson(item)).toList();
    } else {
      throw Exception('Failed to load places');
    }
  }
}
