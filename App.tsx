import React, {Component} from 'react';
import {StyleSheet, TouchableOpacity, View, Text} from 'react-native';
import CubeSwiper from "./components/CubeSwiper";

interface Props {}

interface State {
  page: number
}

export default class App extends Component<Props, State> {
  constructor(props: Props) {
    super(props);

    this.state = {page: 0}
  }

  render() {
    return (
      <View style={styles.container}>
        <CubeSwiper onPageChange={(page) => console.log('change page: ', page)} startPage={this.state.page}>
          <View style={{backgroundColor: 'blue', justifyContent: 'center', alignItems: 'center'}}>
            <View style={{width: 50, height: 50, backgroundColor: 'red'}}/>
            <View style={{width: 50, height: 50, backgroundColor: 'yellow'}}/>
            <TouchableOpacity onPress={() => this.setState({page: 1})}>
              <Text>Click</Text>
            </TouchableOpacity>
          </View>
          <View style={{backgroundColor: 'green', justifyContent: 'center', alignItems: 'center'}}>
            <View style={{width: 50, height: 50, backgroundColor: 'red'}}/>
            <View style={{width: 50, height: 50, backgroundColor: 'yellow'}}/>
          </View>
          <View style={{backgroundColor: 'yellow', justifyContent: 'center', alignItems: 'center'}}>
            <View style={{width: 50, height: 50, backgroundColor: 'red'}}/>
            <View style={{width: 50, height: 50, backgroundColor: 'yellow'}}/>
          </View>
        </CubeSwiper>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#000',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});